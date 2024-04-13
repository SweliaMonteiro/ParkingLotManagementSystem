package com.example.respositories;

import com.example.models.Bill;
import org.springframework.stereotype.Repository;
import java.util.*;


@Repository
public class BillRepository {

    // Mocking the DB
    private Map<Long, Bill> map;

    public BillRepository() {
        map = new HashMap<>();
    }

    public Bill save(Bill bill) {
        if(bill.getId()==0) {
            bill.setId(map.size()+1);
        }
        map.put(bill.getId(), bill);
        return bill;
    }

    public Optional<Bill> findById(long billId) {
        return Optional.ofNullable(map.get(billId));
    }
}
