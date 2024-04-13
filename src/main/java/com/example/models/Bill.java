package com.example.models;

import com.example.enums.BillStatus;
import lombok.Getter;
import lombok.Setter;
import java.util.*;

@Getter
@Setter
public class Bill extends BaseModel {

    private String billNumber;

    private Ticket ticket;

    private Date exitTime;

    private double amount;

    private BillStatus billStatus;

    private List<Payment> payments;

    private Gate generatedAt;

    private ParkingAttendant parkingAttendant;

}