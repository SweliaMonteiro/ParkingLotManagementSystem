package com.example.respositories;

import com.example.models.Gate;
import org.springframework.stereotype.Repository;
import java.util.*;


@Repository
public class GateRepository {

    // Mocking the DB
    private Map<Long, Gate> map;

    public GateRepository() {
        map = new HashMap<>();
    }

    public Optional<Gate> findById(long gateId) {
        return Optional.ofNullable(map.get(gateId));
    }
}
