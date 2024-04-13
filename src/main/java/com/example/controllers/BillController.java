package com.example.controllers;

import com.example.dtos.*;
import com.example.enums.ResponseStatus;
import com.example.models.Bill;
import com.example.services.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
public class BillController {

    @Autowired
    BillService billService;


    public GenerateBillResponseDTO generateBill(GenerateBillRequestDTO requestDTO) {
        GenerateBillResponseDTO responseDTO = new GenerateBillResponseDTO();
        try {
            Bill bill = billService.generateBill(requestDTO.getTicketId(), requestDTO.getGateId());
            responseDTO.setBillId(bill.getId());
            responseDTO.setAmount(bill.getAmount());
            responseDTO.setResponseStatus(ResponseStatus.SUCCESS);
        }
        catch(Exception e) {
            responseDTO.setResponseStatus(ResponseStatus.FAILURE);
            responseDTO.setResponseMessage(e.getMessage());
        }
        return responseDTO;
    }
}
