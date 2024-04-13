package com.example.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenerateTicketRequestDto {

    private long gateId;

    private String registrationNumber;

    private String vehicleType;

}
