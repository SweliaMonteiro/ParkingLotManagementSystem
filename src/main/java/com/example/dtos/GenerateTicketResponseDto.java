package com.example.dtos;

import com.example.enums.ResponseStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenerateTicketResponseDto {

    private long ticketId;

    private ResponseStatus responseStatus;

    private String responseMessage;

}
