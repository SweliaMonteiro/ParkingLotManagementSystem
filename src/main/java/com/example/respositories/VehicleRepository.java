package com.example.respositories;

import com.example.models.Vehicle;
import org.springframework.stereotype.Repository;
import java.util.*;


@Repository
public class VehicleRepository {

    // Mocking the DB
    private Map<Long, Vehicle> map;

    public VehicleRepository() {
        map = new HashMap<>();
    }

    public Optional<Vehicle> getVehicleByRegistrationNumber(String registrationNumber) {
        for(Vehicle vehicle:map.values()) {
            if(vehicle.getRegistrationNumber().equals(registrationNumber)) {
                return Optional.of(vehicle);
            }
        }
        return Optional.empty();
    }

    public Vehicle save(Vehicle vehicle) {
        if(vehicle.getId()==0) {
            vehicle.setId(map.size()+1);
        }
        map.put(vehicle.getId(), vehicle);
        return vehicle;
    }
}