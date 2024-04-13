package com.example.dtos;

import lombok.Getter;
import lombok.Setter;
import java.util.*;

@Getter
@Setter
public class GetParkingLotCapacityRequestDto {

    private long parkingLotId;

    private List<Long> parkingFloorIds;

    private List<String> vehicleTypes;

}
