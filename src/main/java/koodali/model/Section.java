package koodali.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class Section {
    private String name;
    @OneToMany
    @JoinColumn(name = "id")
    private Map<String, Student> students = new HashMap<>();
    @OneToMany
    @JoinColumn(name = "id")
    private Map<String, Teacher> teachers = new HashMap<>();
    private String linkOrAddress;
    @ElementCollection
    @CollectionTable(
            name = "classSchedule",
            joinColumns = @JoinColumn(name = "id")
    )
    @Column(name = "class")
    private List<LocalDateTime> classSchedule;
    @Id
    @GeneratedValue
    private int id;

    public Section(String name, HashMap<String, Student> students, HashMap<String, Teacher> teachers, String linkOrAddress, List<LocalDateTime> classSchedule) {
        this.name = name;
        this.students = students;
        this.teachers = teachers;
        this.linkOrAddress = linkOrAddress;
        this.classSchedule = classSchedule;
    }

    public Section(String name) {
        this.name = name;
        this.linkOrAddress = "";
    }

    public Section(String name, String linkOrAddress) {
        this.name = name;

        this.linkOrAddress = linkOrAddress;
    }

    public Section() {

    }

    public String getLinkOrAddress() {
        return linkOrAddress;
    }

    public void setLinkOrAddress(String linkOrAddress) {
        this.linkOrAddress = linkOrAddress;
    }

    public List<LocalDateTime> getClassSchedule() {
        return classSchedule;
    }

    public void setClassSchedule(List<LocalDateTime> classSchedule) {
        this.classSchedule = classSchedule;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Student> getStudents() {
        return students;
    }

    public void setStudents(Map<String, Student> students) {
        this.students = students;
    }

    public Map<String, Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Map<String, Teacher> teachers) {
        this.teachers = teachers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
