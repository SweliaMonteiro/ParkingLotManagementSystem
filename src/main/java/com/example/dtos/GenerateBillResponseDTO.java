package com.example.dtos;

import com.example.enums.ResponseStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenerateBillResponseDTO {

    private ResponseStatus responseStatus;

    private String responseMessage;

    private long billId;

    private double amount;

}
