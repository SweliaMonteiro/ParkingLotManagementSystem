package com.example.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenerateBillRequestDTO {

    private long ticketId;

    private long gateId;

}
