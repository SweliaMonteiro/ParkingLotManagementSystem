package com.example.strategies.paymentMode;

import com.example.enums.PaymentMode;
import com.example.enums.PaymentStatus;
import com.example.models.Bill;
import com.example.models.Payment;

public class OnlinePaymentModeStrategy implements PaymentModeStrategy {

    @Override
    public Payment payBill(Bill bill, double amount) {
        // Create a payment object and set the bill, amount, payment mode, reference id and payment status as per the online payment mode
        Payment payment = new Payment();
        payment.setBill(bill);
        payment.setAmount(amount);
        payment.setPaymentMode(PaymentMode.ONLINE);
        payment.setReferenceId("ONLINE" + bill.getId());
        // API calls to make payment, if successful set payment status to success or else to failure
        payment.setPaymentStatus(PaymentStatus.SUCCESS);
        return payment;
    }
}
