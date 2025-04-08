package entity;


import java.time.LocalDateTime;
import java.util.Scanner;

public class Schedule implements IApp{
    public static int count = 0;
    private int scheduleId;
    private int classroomId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Schedule() {
        this.scheduleId = ++count;
    }

    public Schedule(int classroomId, LocalDateTime startTime, LocalDateTime endTime) {
        this.scheduleId = ++count;
        this.classroomId = classroomId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public int getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(int classroomId) {
        this.classroomId = classroomId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public void inputData(Scanner sc) {
        this.classroomId = Integer.parseInt(sc.nextLine());

        this.startTime = LocalDateTime.now();


    }



    @Override
    public String toString() {
        return "ScheduleId: " + scheduleId + ", ClassroomId: " + classroomId + ", StartTime: " + startTime + ", EndTime: " + endTime;
    }
}