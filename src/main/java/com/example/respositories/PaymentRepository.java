package com.example.respositories;

import com.example.models.Payment;
import org.springframework.stereotype.Repository;
import java.util.*;


@Repository
public class PaymentRepository {

    // Mocking the DB
    private Map<Long, Payment> map;

    public PaymentRepository() {
        map = new HashMap<>();
    }

    public Payment save(Payment payment) {
        if(payment.getId()==0) {
            payment.setId(map.size()+1);
        }
        map.put(payment.getId(), payment);
        return payment;
    }
}
