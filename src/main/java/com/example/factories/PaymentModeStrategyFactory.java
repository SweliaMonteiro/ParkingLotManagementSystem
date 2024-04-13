package com.example.factories;

import com.example.enums.PaymentMode;
import com.example.strategies.paymentMode.*;

public class PaymentModeStrategyFactory {

    public static PaymentModeStrategy getPaymentModeStrategy(PaymentMode paymentMode) {
        if(paymentMode.equals(PaymentMode.ONLINE)) {
            return new OnlinePaymentModeStrategy();
        }
        else if(paymentMode.equals(PaymentMode.CASH)) {
            return new CashPaymentModeStrategy();
        }
        return null;
    }

}
