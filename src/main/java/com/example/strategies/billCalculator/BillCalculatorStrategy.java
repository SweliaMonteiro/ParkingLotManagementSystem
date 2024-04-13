package com.example.strategies.billCalculator;

import java.util.Date;

public interface BillCalculatorStrategy {

    double calculateAmount(Date entryTime, Date exitTime);

}
