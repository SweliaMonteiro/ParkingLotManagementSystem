package com.example.strategies.billCalculator;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class BillCalculatorStrategyImpl implements BillCalculatorStrategy {

    @Override
    public double calculateAmount(Date entryTime, Date exitTime) {
        double amount = 0;

        // Calculate the difference between the entry and exit time in hours and minutes
        Calendar calEntryTime = Calendar.getInstance();
        calEntryTime.setTime(entryTime);
        LocalTime localTimeEntryTime = LocalTime.of(calEntryTime.get(Calendar.HOUR_OF_DAY), calEntryTime.get(Calendar.MINUTE), calEntryTime.get(Calendar.SECOND));

        Calendar calExitTime = Calendar.getInstance();
        calExitTime.setTime(exitTime);
        LocalTime localTimeExitTime = LocalTime.of(calExitTime.get(Calendar.HOUR_OF_DAY), calExitTime.get(Calendar.MINUTE), calExitTime.get(Calendar.SECOND));

        double hoursDiff = ChronoUnit.HOURS.between(localTimeEntryTime, localTimeExitTime);
        double minutesDiff  = (ChronoUnit.MINUTES.between(localTimeEntryTime, localTimeExitTime) % 60.0) / 100.0;

        // Calculate the amount based on the hours and minutes difference considering each hour or part of it costs 10
        return (int)Math.ceil(hoursDiff+minutesDiff) * 10;
    }
}
