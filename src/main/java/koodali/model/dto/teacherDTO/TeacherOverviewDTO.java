package koodali.model.dto.teacherDTO;

import java.time.LocalDate;

public record TeacherOverviewDTO(
        String id,
        String firstName,
        String lastName,
        LocalDate joinDate,
        String sections
) {
}
