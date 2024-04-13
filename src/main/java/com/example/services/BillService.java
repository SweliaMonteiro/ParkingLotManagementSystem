package com.example.services;

import com.example.enums.*;
import com.example.exceptions.*;
import com.example.models.*;
import com.example.respositories.*;
import com.example.strategies.billCalculator.BillCalculatorStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;


@Service
public class BillService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private GateRepository gateRepository;

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private ParkingSpotRepository parkingSpotRepository;

    @Autowired
    private BillCalculatorStrategy billCalculatorStrategy;


    public Bill generateBill(long ticketId, long gateId) throws InvalidTicketException, InvalidGateException {
        // Check if the ticket is valid
        Optional<Ticket> optionalTicket = ticketRepository.findById(ticketId);
        if(optionalTicket.isEmpty()) {
            throw new InvalidTicketException("Invalid Ticket Id");
        }
        Ticket ticket = optionalTicket.get();

        // Check if the gate is valid
        Optional<Gate> optionalGate = gateRepository.findById(gateId);
        if(optionalGate.isEmpty()) {
            throw new InvalidGateException("Invalid Gate Id");
        }
        Gate gate = optionalGate.get();

        // Generate a new bill using the required details and save it in the DB
        Bill bill = new Bill();
        bill.setExitTime(new Date());
        bill.setTicket(ticket);
        bill.setGeneratedAt(gate);
        bill.setParkingAttendant(gate.getParkingAttendant());
        // Calculate the amount for the bill using the BillCalculatorStrategy using the entry and exit time
        bill.setAmount(billCalculatorStrategy.calculateAmount(ticket.getEntryTime(), bill.getExitTime()));
        // Set bill status to UNPAID as the payment is yet to be done
        bill.setBillStatus(BillStatus.UNPAID);
        bill = billRepository.save(bill);

        // After generating the bill, update the parking spot status to AVAILABLE and save it in the DB
        ParkingSpot parkingSpot = ticket.getParkingSpot();
        parkingSpot.setStatus(ParkingSpotStatus.AVAILABLE);
        parkingSpotRepository.save(parkingSpot);

        return bill;
    }
}
