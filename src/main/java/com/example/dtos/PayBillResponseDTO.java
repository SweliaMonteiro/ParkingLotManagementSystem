package com.example.dtos;

import com.example.enums.PaymentMode;
import com.example.enums.PaymentStatus;
import com.example.enums.ResponseStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class PayBillResponseDTO {

    private ResponseStatus responseStatus;

    private String responseMessage;

    private Map<PaymentMode, PaymentStatus> paymentStatusPerPaymentMode;

}
