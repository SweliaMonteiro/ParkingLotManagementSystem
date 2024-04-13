package com.example.models;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class Ticket extends BaseModel {

    private Vehicle vehicle;

    private Date entryTime;

    private ParkingSpot parkingSpot;

    private Gate gate;

    private ParkingAttendant parkingAttendant;

}
