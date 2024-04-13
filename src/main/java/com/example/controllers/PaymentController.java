package com.example.controllers;

import com.example.enums.*;
import com.example.models.*;
import com.example.services.PaymentService;
import com.example.dtos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import java.util.*;


@Controller
public class PaymentController {

    @Autowired
    private PaymentService paymentService;


    public PayBillResponseDTO payBill(PayBillRequestDTO requestDTO) {
        PayBillResponseDTO responseDTO = new PayBillResponseDTO();
        try {
            List<Payment> payments = paymentService.payBill(requestDTO.getBillId(), requestDTO.getAmountPerPaymentMode());
            Map<PaymentMode, PaymentStatus> paymentStatusPerPaymentMode = new HashMap<>();
            for(Payment payment:payments) {
                paymentStatusPerPaymentMode.put(payment.getPaymentMode(), payment.getPaymentStatus());
            }
            responseDTO.setPaymentStatusPerPaymentMode(paymentStatusPerPaymentMode);
            responseDTO.setResponseStatus(ResponseStatus.SUCCESS);
        }
        catch(Exception e) {
            responseDTO.setResponseStatus(ResponseStatus.FAILURE);
            responseDTO.setResponseMessage(e.getMessage());
        }
        return responseDTO;
    }
}
