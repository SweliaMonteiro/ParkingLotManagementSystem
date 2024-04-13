package com.example.services;

import com.example.enums.*;
import com.example.exceptions.*;
import com.example.models.*;
import com.example.respositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;


@Service
public class ParkingLotService {

    @Autowired
    private ParkingLotRepository parkingLotRepository;


    public Map<ParkingFloor, Map<String, Integer>> getParkingLotCapacity(long parkingLotId, List<Long> parkingFloorIds, List<VehicleType> requiredVehicleTypes) throws InvalidParkingLotException {
        // Check if the parking lot is valid
        Optional<ParkingLot> optionalParkingLot = parkingLotRepository.findById(parkingLotId);
        if(optionalParkingLot.isEmpty()) {
            throw new InvalidParkingLotException("Invalid Parking Lot Id");
        }
        ParkingLot parkingLot = optionalParkingLot.get();

        // If parkingFloorIds is not provided then get capacity for all parking floors, convert the list to set for faster lookup
        Set<Long> parkingFloorIdSet = new HashSet<>();
        if(parkingFloorIds != null) {
            parkingFloorIdSet = new HashSet<>(parkingFloorIds);
        }

        // If requiredVehicleTypes is not provided then get capacity for all vehicle types
        if(requiredVehicleTypes==null || requiredVehicleTypes.isEmpty()) {
            requiredVehicleTypes = Arrays.asList(VehicleType.values());
        }
        
        Map<ParkingFloor, Map<String, Integer>> parkingLotCapacity = new HashMap<>();
        // Go through each parking floor of the parking lot
        for(ParkingFloor parkingFloor:parkingLot.getParkingFloors()) {
            // If parkingFloor is not in the provided list then skip it
            if(!parkingFloorIdSet.isEmpty() && !parkingFloorIdSet.contains(parkingFloor.getId())) {
                continue;
            }
            
            // For each parking floor, get the count of available spots for each vehicle type
            Map<String, Integer> vehicleTypeIntegerMap = new HashMap<>();
            for(VehicleType vehicleType:requiredVehicleTypes){
                int count = 0;
                // If the parking floor is operational then count the number of available spots
                if(vehicleType!=null && parkingFloor.getStatus().equals(FloorStatus.OPERATIONAL)) {
                    // Iterate through each spot of the parking floor
                    for(ParkingSpot spot:parkingFloor.getSpots()) {
                        // If the spot supports the required vehicle type and is available then increment the count
                        if(spot.getSupportedVehicleType().equals(vehicleType) && spot.getStatus().equals(ParkingSpotStatus.AVAILABLE)) {
                            count++;
                        }
                    }
                    // Add the count to the vehicleTypeIntegerMap for the vehicle type
                    vehicleTypeIntegerMap.put(vehicleType.name(), count);
                }
            }
            // Add the vehicleTypeIntegerMap to the parkingLotCapacity map for the parking floor
            parkingLotCapacity.put(parkingFloor, vehicleTypeIntegerMap);
        }

        return parkingLotCapacity;
    }
}
