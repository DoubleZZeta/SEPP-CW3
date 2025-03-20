package model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

public class TimeSlot {
    public enum TimeSlotStatus {
        UNCHOSEN,
        CHOSEN
    };
    public DayOfWeek day;
    public LocalDate startDate;
    public LocalTime startTime;
    public LocalDate endDate;
    public LocalTime endTime;
    public final String courseCode;
    public final int activityId;
    public TimeSlotStatus status;

    public TimeSlot(DayOfWeek day, LocalDate startDate, LocalTime startTime,
                    LocalDate endDate, LocalTime endTime, String courseCode,
                    int activityId, TimeSlotStatus status)
    {
        this.day = day;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
        this.courseCode = courseCode;
        this.activityId = activityId;
        this.status = status;
    }

    public boolean hasCourseCode(String courseCode) {
        return this.courseCode.equals(courseCode);
    }

    public boolean hasActivityId(int activityId) {
        return this.activityId == activityId;
    }

    public boolean isChosen() {
        return status == TimeSlotStatus.CHOSEN;
    }
}
