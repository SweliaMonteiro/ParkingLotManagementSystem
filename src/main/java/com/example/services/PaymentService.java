package com.example.services;

import com.example.factories.PaymentModeStrategyFactory;
import com.example.models.*;
import com.example.enums.*;
import com.example.respositories.*;
import java.util.*;
import com.example.exceptions.*;
import com.example.strategies.paymentMode.PaymentModeStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PaymentService {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private PaymentRepository paymentRepository;


    public List<Payment> payBill(long billId, Map<PaymentMode, Integer> amountPerPaymentMode) throws InvalidBillException, BillAlreadyPaidException, InvalidAmountException {
        // Check if the bill is valid
        Optional<Bill> optionalBill = billRepository.findById(billId);
        if(optionalBill.isEmpty()) {
            throw new InvalidBillException("Invalid Bill Id");
        }
        Bill bill = optionalBill.get();

        // Check if the bill is already paid
        if(bill.getBillStatus().equals(BillStatus.PAID)) {
            throw new BillAlreadyPaidException("Bill is already paid");
        }

        // The total amount paid should be equal to the parking fee. If not, throw an exception
        int totalAmountPaid = 0;
        for(PaymentMode paymentMode:amountPerPaymentMode.keySet()) {
            totalAmountPaid += amountPerPaymentMode.get(paymentMode);
        }
        if(totalAmountPaid != bill.getAmount()) {
            throw new InvalidAmountException("Amount paid is not equal to the bill amount");
        }

        int paymentSuccessCount = 0; // To keep count of successful payments
        List<Payment> payments = new ArrayList<>();
        for(PaymentMode paymentMode:amountPerPaymentMode.keySet()) {
            // For each PaymentMode, get the PaymentModeStrategy and pay the bill with the amount specified in the map
            PaymentModeStrategy paymentModeStrategy = PaymentModeStrategyFactory.getPaymentModeStrategy(paymentMode);
            Payment payment = paymentModeStrategy.payBill(bill, amountPerPaymentMode.get(paymentMode));
            // If payment is successful then increment the count
            if(payment.getPaymentStatus().equals(PaymentStatus.SUCCESS)) {
                paymentSuccessCount++;
            }
            // Save the payment in the DB and add it to the list of payments
            payments.add(paymentRepository.save(payment));
        }

        // If all payments are successful then bill is paid
        if(paymentSuccessCount == amountPerPaymentMode.size()) {
            bill.setBillStatus(BillStatus.PAID);
        }
        // If there are no successful payments then bill is unpaid
        else if(paymentSuccessCount == 0) {
            bill.setBillStatus(BillStatus.UNPAID);
        }
        // Else the bill is partially paid
        else {
            bill.setBillStatus(BillStatus.PARTIALLY_PAID);
        }

        // Update the bill with the list of payments and save the updated bill in DB
        bill.setPayments(payments);
        billRepository.save(bill);
        return payments;
    }
}