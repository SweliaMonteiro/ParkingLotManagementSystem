package com.example.controllers;

import com.example.dtos.*;
import com.example.enums.ResponseStatus;
import com.example.models.Ticket;
import com.example.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
public class TicketController {

    @Autowired
    private TicketService ticketService;


    public GenerateTicketResponseDto generateTicket(GenerateTicketRequestDto requestDTO) {
        GenerateTicketResponseDto responseDTO = new GenerateTicketResponseDto();
        try {
            Ticket ticket = ticketService.generateTicket(requestDTO.getGateId(), requestDTO.getRegistrationNumber(), requestDTO.getVehicleType());
            responseDTO.setTicketId(ticket.getId());
            responseDTO.setResponseStatus(ResponseStatus.SUCCESS);
        }
        catch(Exception e) {
            responseDTO.setResponseStatus(ResponseStatus.FAILURE);
            responseDTO.setResponseMessage(e.getMessage());
        }
        return responseDTO;
    }
}
