package com.example.respositories;

import com.example.models.ParkingSpot;
import org.springframework.stereotype.Repository;
import java.util.*;


@Repository
public class ParkingSpotRepository {

    // Mocking the DB
    private Map<Long, ParkingSpot> map;

    public ParkingSpotRepository() {
        map = new HashMap<>();
    }

    public ParkingSpot save(ParkingSpot parkingSpot) {
        if(parkingSpot.getId()==0) {
            parkingSpot.setId(map.size()+1);
        }
        map.put(parkingSpot.getId(), parkingSpot);
        return parkingSpot;
    }
}
