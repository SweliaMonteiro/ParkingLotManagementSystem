package com.example.respositories;

import com.example.models.*;
import org.springframework.stereotype.Repository;
import java.util.*;


@Repository
public class ParkingLotRepository {

    // Mocking the DB
    private Map<Long, ParkingLot> map;

    public ParkingLotRepository() {
        map = new HashMap<>();
    }

    public Optional<ParkingLot> findParkingLotByGateId(long gateId) {
        for(ParkingLot parkingLot:map.values()) {
            for(Gate gate:parkingLot.getGates()) {
                if(gate.getId()==gateId) {
                    return Optional.ofNullable(parkingLot);
                }
            }
        }
        return Optional.empty();
    }

    public Optional<ParkingLot> findById(long parkingLotId) {
        return Optional.ofNullable(map.get(parkingLotId));
    }
}
