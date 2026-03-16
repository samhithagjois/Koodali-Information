package koodali.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class AttendanceEntity {



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getWeek() {
        return week;
    }

    public void setWeek(LocalDate week) {
        this.week = week;
    }

    public boolean isAttended() {
        return attended;
    }

    public void setAttended(boolean attended) {
        this.attended = attended;
    }
    @Id
    private int id;
    private String studentID;
    private String name;
    private LocalDate week;

    private boolean attended;

    public AttendanceEntity(String studentID, String name, LocalDate week, boolean attended) {
        this.studentID = studentID;
        this.name = name;
        this.week = week;
        this.attended = attended;
    }

    public AttendanceEntity() {
        this.studentID = "";
        this.name = "";
        this.week = LocalDate.of(1970,1,1);
        this.attended = false;
    }

}
