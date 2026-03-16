package koodali.model.dto.studentDTO;

public record SectionStudentOverviewDTO(
        String personID,
        String firstName,
        String lastName,
        int homeworkPoints,
        double attendancePercentage
) {
}
