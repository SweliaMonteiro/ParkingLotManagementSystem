package com.example.strategies.spotAssignment;

import com.example.enums.*;
import com.example.models.*;
import java.util.*;

public class NearestSpotAssignmentStrategy implements SpotAssignmentStrategy {

    @Override
    public Optional<ParkingSpot> assignSpot(ParkingLot parkingLot, VehicleType vehicleType) {
        // To store the list of available parking spots for each floor number
        TreeMap<Integer, List<ParkingSpot>> availableParkingSpotsPerFloorNumber = new TreeMap<Integer, List<ParkingSpot>>();

        // Loop through the each floor of the given parking lot
        for(ParkingFloor parkingFloor:parkingLot.getParkingFloors()) {
            // Only if the floor is operational move forward
            if(parkingFloor.getStatus().equals(FloorStatus.OPERATIONAL)) {
                // Get the list of all the available parking spots on the current floor which allows only the vehicles with given vehicle type
                List<ParkingSpot> spots = new ArrayList<ParkingSpot>();
                for(ParkingSpot parkingSpot:parkingFloor.getSpots()) {
                    if(parkingSpot.getStatus().equals(ParkingSpotStatus.AVAILABLE) && parkingSpot.getSupportedVehicleType().equals(vehicleType)) {
                        spots.add(parkingSpot);
                    }
                }
                // If no spots available then dont add the current floor in the main list
                if(spots.size() > 0) {
                    availableParkingSpotsPerFloorNumber.put(parkingFloor.getFloorNumber(), spots);
                }
            }
        }

        // If no spots available then return null
        if(availableParkingSpotsPerFloorNumber.isEmpty() || availableParkingSpotsPerFloorNumber.size()==0) {
            return Optional.empty();
        }

        // It should assign vehicle to the floor with the least number of available spots for that vehicle type

        // Therefore store the count of available spots as key and list of spots as value
        TreeMap<Integer, List<ParkingSpot>> parkingSpotsCountPerFloorSpots = new TreeMap<Integer, List<ParkingSpot>>();

        // Loop through the above generated list i.e. availableParkingSpotsPerFloorNumber, as it is a TreeMap the data is sorted as per floor number in ascending order
        for(int floorNumber:availableParkingSpotsPerFloorNumber.keySet()) {
            // If there are multiple floors with same number of available spots, it should assign the vehicle to the floor with the lowest floor number

            // To achieve the above requirement its enough to add only one time the total count of available spots as a key as we want only one spot for assignment
            // Since the previous list(i.e. availableParkingSpotsPerFloorNumber) is sorted as per the floor number hence the count of spots of the lowest floor will be added first
            // And if the number of available spots matches with other floors they wont be added
            if(!parkingSpotsCountPerFloorSpots.containsKey(availableParkingSpotsPerFloorNumber.get(floorNumber).size())) {
                parkingSpotsCountPerFloorSpots.put(availableParkingSpotsPerFloorNumber.get(floorNumber).size(), availableParkingSpotsPerFloorNumber.get(floorNumber));
            }
        }

        // Get the least key (i.e. count of spots) from the TreeMap
        int lowestKey = parkingSpotsCountPerFloorSpots.firstKey();

        // Get the first available parking spot from the list with the least count of spots
        ParkingSpot parkingSpot = parkingSpotsCountPerFloorSpots.get(lowestKey).get(0);

        // Mark the parking spot as occupied
        parkingSpot.setStatus(ParkingSpotStatus.OCCUPIED);

        return Optional.ofNullable(parkingSpot);
    }
}
