package com.example.dtos;

import com.example.enums.ResponseStatus;
import com.example.models.ParkingFloor;
import lombok.Getter;
import lombok.Setter;
import java.util.Map;

@Getter
@Setter
public class GetParkingLotCapacityResponseDto {

    private ResponseStatus responseStatus;

    private String responseMessage;

    private Map<ParkingFloor, Map<String, Integer>> capacityMap;

}
