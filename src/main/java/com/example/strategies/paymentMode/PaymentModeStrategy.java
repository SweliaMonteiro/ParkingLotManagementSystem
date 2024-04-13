package com.example.strategies.paymentMode;

import com.example.models.Bill;
import com.example.models.Payment;

public interface PaymentModeStrategy {

    Payment payBill(Bill bill, double amount);

}
