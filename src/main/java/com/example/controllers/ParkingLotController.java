package com.example.controllers;

import com.example.dtos.*;
import com.example.enums.*;
import com.example.models.ParkingFloor;
import com.example.services.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import java.util.*;


@Controller
public class ParkingLotController {

    @Autowired
    private ParkingLotService parkingLotService;


    public GetParkingLotCapacityResponseDto getParkingLotCapacity(GetParkingLotCapacityRequestDto requestDTO) {
        GetParkingLotCapacityResponseDto responseDTO = new GetParkingLotCapacityResponseDto();
        try {
            List<VehicleType> vehicleTypes = new ArrayList<>();
            if(requestDTO.getVehicleTypes() != null) {
                for(String vehicleType:requestDTO.getVehicleTypes()) {
                    vehicleTypes.add(VehicleType.valueOf(vehicleType));
                }
            }
            Map<ParkingFloor, Map<String, Integer>> parkingLotCapacity = parkingLotService.getParkingLotCapacity(requestDTO.getParkingLotId(), requestDTO.getParkingFloorIds(), vehicleTypes);
            responseDTO.setCapacityMap(parkingLotCapacity);
            responseDTO.setResponseStatus(ResponseStatus.SUCCESS);
        }
        catch(Exception e) {
            responseDTO.setResponseStatus(ResponseStatus.FAILURE);
            responseDTO.setResponseMessage(e.getMessage());
        }
        return responseDTO;
    }
}
