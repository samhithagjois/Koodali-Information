package koodali.model.dto;

import java.time.LocalDate;

public record HomeworkPointsDTO
        (
                String studentId,
                String studentName,
                LocalDate week,
                int points
        ) {
}
