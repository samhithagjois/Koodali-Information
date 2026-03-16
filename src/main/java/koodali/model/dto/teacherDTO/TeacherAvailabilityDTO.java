package koodali.model.dto.teacherDTO;

import java.time.LocalDate;

public record TeacherAvailabilityDTO(
String id,
String name,

LocalDate week,

boolean available
) {
}
