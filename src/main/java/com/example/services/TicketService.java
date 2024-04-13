package com.example.services;

import com.example.enums.*;
import com.example.exceptions.*;
import com.example.models.*;
import com.example.respositories.*;
import com.example.strategies.spotAssignment.SpotAssignmentStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;


@Service
public class TicketService {

    @Autowired
    private GateRepository gateRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private ParkingLotRepository parkingLotRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private SpotAssignmentStrategy spotAssignmentStrategy;


    public Ticket generateTicket(long gateId, String registrationNumber, String vehicleType) throws InvalidGateException, InvalidParkingLotException, ParkingSpotNotAvailableException {
        // Check if the gate is valid
        Optional<Gate> optionalGate = gateRepository.findById(gateId);
        if(optionalGate.isEmpty()) {
            throw new InvalidGateException("Invalid Gate Id");
        }
        Gate gate = optionalGate.get();
        // Check if the gate is an exit gate as customer can only enter from entry gate
        if(gate.getType().equals(GateType.EXIT)) {
            throw new InvalidGateException("Customer is trying to enter from exit gate");
        }

        // Check if the parking lot is valid
        Optional<ParkingLot> optionalParkingLot = parkingLotRepository.findParkingLotByGateId(gateId);
        if(optionalParkingLot.isEmpty()) {
            throw new InvalidParkingLotException("Invalid Parking Lot");
        }
        ParkingLot parkingLot = optionalParkingLot.get();

        // Check if the vehicle is already registered, if not then register the vehicle and store it in the DB
        Vehicle vehicle;
        Optional<Vehicle> optionalVehicle = vehicleRepository.getVehicleByRegistrationNumber(registrationNumber);
        if(optionalVehicle.isEmpty()) {
            vehicle = new Vehicle();
            vehicle.setRegistrationNumber(registrationNumber);
            vehicle.setVehicleType(VehicleType.valueOf(vehicleType));
            vehicle = vehicleRepository.save(vehicle);
        }
        else {
            vehicle = optionalVehicle.get();
        }

        // Parking spot should be assigned to the floor with the least number of available spots for the vehicle type and the spot should be nearest to the entry gate for the given vehicle type
        Optional<ParkingSpot> optionalParkingSpot = spotAssignmentStrategy.assignSpot(parkingLot, VehicleType.valueOf(vehicleType));
        // If no parking spot is available then throw an exception
        if(optionalParkingSpot.isEmpty()) {
            throw new ParkingSpotNotAvailableException("Parking spot not available");
        }
        // Else assign the parking spot to the vehicle
        ParkingSpot parkingSpot = optionalParkingSpot.get();

        // Generate a new ticket using the required details and save it in the DB
        Ticket ticket = new Ticket();
        ticket.setVehicle(vehicle);
        ticket.setEntryTime(new Date());
        ticket.setParkingSpot(parkingSpot);
        ticket.setGate(gate);
        ticket.setParkingAttendant(gate.getParkingAttendant());
        return ticketRepository.save(ticket);
    }
}
