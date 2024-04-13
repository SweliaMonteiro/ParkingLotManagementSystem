package com.example.models;

import com.example.enums.PaymentMode;
import com.example.enums.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Payment extends BaseModel {

    private String referenceId;

    private double amount;

    private Bill bill;

    private PaymentMode paymentMode;

    private PaymentStatus paymentStatus;

}