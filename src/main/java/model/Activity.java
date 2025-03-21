package model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

public abstract class Activity {
    private int id;
    private LocalDate startDate;
    private LocalTime startTime;
    private LocalDate endDate;
    private LocalTime endTime;
    private String location;
    private DayOfWeek day;

    public Activity(int id, LocalDate startDate, LocalTime startTime, LocalDate endDate,
                    LocalTime endTime, String location, DayOfWeek day)
    {
        this.id = id;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
        this.location = location;
        this.day = day;
    }

    public boolean hasId(int id) {
        return this.id == id;
    }

    public String toString() {
        // not sure exactly what this is supposed to do
        return "NOT IMPLEMENTED";
    }
}
