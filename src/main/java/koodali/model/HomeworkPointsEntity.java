package koodali.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class HomeworkPointsEntity {

    @Id

    private int id;

    private String studentID;
    private String name;
    private LocalDate week;

    private int points;

    public HomeworkPointsEntity(String studentID, String name, LocalDate week, int points) {
        this.studentID = studentID;
        this.name = name;
        this.week = week;
        this.points = points;
    }

    public HomeworkPointsEntity() {
        this.studentID = "";
        this.name = "";
        this.week = LocalDate.of(1970, 1, 1);
        this.points = 0;
    }

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

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
