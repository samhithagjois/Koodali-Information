package koodali.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Teacher extends Person {


    @ManyToMany

    private final List<Student> studentOverview = new ArrayList<>();
    @ElementCollection
    @CollectionTable(
            name = "teacher_sections",
            joinColumns = @JoinColumn(name = "teacher_id")
    )
    @Column(name = "section")
    private List<String> sections;
    private int amountOfTextbooks = 0;

    private LocalDate joinDate;

    public Teacher(String personID, String firstName, String lastName, List<String> sections) {
        super(personID, firstName, lastName);
        this.sections = sections;
    }

    public Teacher( String firstName, String lastName) {
        super("NKS_"+ (UUID.randomUUID().toString().substring(0,2)), firstName, lastName);
        this.sections = List.of();
    }

    public Teacher() {
        super();
        this.personID = "NKS_T_" + UUID.randomUUID();

    }

    public List<String> getSections() {
        return sections;
    }

    public void setSections(List<String> section) {
        this.sections = section;
    }

    public List<Student> getStudentOverview() {
        return studentOverview;
    }

    public int getAmountOfTextbooks() {
        return amountOfTextbooks;
    }

    public void setAmountOfTextbooks(int amountOfTextbooks) {
        this.amountOfTextbooks = amountOfTextbooks;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
    }
}
