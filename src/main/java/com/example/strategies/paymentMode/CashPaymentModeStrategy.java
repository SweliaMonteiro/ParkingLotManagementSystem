package com.example.strategies.paymentMode;

import com.example.enums.PaymentMode;
import com.example.enums.PaymentStatus;
import com.example.models.Bill;
import com.example.models.Payment;

public class CashPaymentModeStrategy implements PaymentModeStrategy {

    @Override
    public Payment payBill(Bill bill, double amount) {
        // Create a payment object and set the bill, amount, payment mode, reference id and payment status as per the cash payment mode
        Payment payment = new Payment();
        payment.setBill(bill);
        payment.setAmount(amount);
        payment.setPaymentMode(PaymentMode.CASH);
        payment.setReferenceId("CASH" + bill.getId());
        payment.setPaymentStatus(PaymentStatus.SUCCESS);
        return payment;
    }
}