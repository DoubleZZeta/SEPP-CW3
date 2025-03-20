package model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Timetable {
    private final String studentEmail;
    private HashMap<DayOfWeek, ArrayList<TimeSlot>> timeslots = new HashMap<>();

    public Timetable(String studentEmail) {
        this.studentEmail = studentEmail;
        for (DayOfWeek day : DayOfWeek.values()) {
            timeslots.put(day, new ArrayList<>());
        }
    }

    public void addTimeSlot(DayOfWeek day, LocalDate startDate, LocalTime startTime,
                            LocalDate endDate, LocalTime endTime, String courseCode,
                            int activityId, TimeSlot.TimeSlotStatus status)
    {
        timeslots.get(day).add(new TimeSlot(day, startDate, startTime, endDate, endTime,
                                            courseCode, activityId, status));
    }

    public int numChosenActivities(String courseCode) {
        int count = 0;
        for (DayOfWeek day : DayOfWeek.values()) {
            for (TimeSlot timeslot : timeslots.get(day)) {
                if (timeslot.isChosen() && timeslot.hasCourseCode(courseCode)) {
                    count++;
                }
            }
        }
        return count;
    }

    public String[] checkConflicts(LocalDate startDate, LocalTime startTime, LocalDate endDate,
                                   LocalTime endTime, DayOfWeek day)
    {
        for (TimeSlot timeslot : timeslots.get(day)) {
            if (!timeslot.isChosen()) continue;
            // TODO: find out if this is supposed to be inclusive or not
            if (endDate.isBefore(timeslot.startDate) || startDate.isAfter(timeslot.endDate)) continue;
            if (endTime.isBefore(timeslot.startTime) || startTime.isAfter(timeslot.endTime)) continue;
            return new String[]{timeslot.courseCode, Integer.toString(timeslot.activityId)};
        }
        return null;
    }

    public boolean hasStudentEmail(String email) {
        return studentEmail.equals(email);
    }

    public boolean chooseActivity(String courseCode, int activityId) {
        // TODO: not entirely sure what the return value is supposed to do. will guess that it is just success/failure
        for (DayOfWeek day : DayOfWeek.values()) {
            for (TimeSlot timeslot : timeslots.get(day)) {
                if (timeslot.hasCourseCode(courseCode) && timeslot.hasActivityId(activityId)) {
                    timeslot.status = TimeSlot.TimeSlotStatus.CHOSEN;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean hasSlotsForCourse(String courseCode) {
        for (DayOfWeek day : DayOfWeek.values()) {
            for (TimeSlot timeslot : timeslots.get(day)) {
                if (timeslot.hasCourseCode(courseCode)) return true;
            }
        }
        return false;
    }

    public void removeSlotsForCourse(String courseCode) {
        for (DayOfWeek day : DayOfWeek.values()) {
            timeslots.get(day).removeIf(timeslot -> timeslot.hasCourseCode(courseCode));
        }
    }

    public String toString() {
        // TODO: implement
        return "TODO";
    }

}
