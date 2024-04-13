package com.example.strategies.spotAssignment;

import com.example.models.ParkingLot;
import com.example.models.ParkingSpot;
import com.example.enums.VehicleType;
import java.util.Optional;

public interface SpotAssignmentStrategy {

    Optional<ParkingSpot> assignSpot(ParkingLot parkingLot, VehicleType vehicleType);

}
