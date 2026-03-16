package koodali.model.dto.teacherDTO;

import java.time.LocalDate;

public record SectionTeacherOverviewDTO(
        String id,
        String name,

        LocalDate joinDate
) {
}
