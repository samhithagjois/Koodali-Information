package koodali.model.dto.studentDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record StudentOverviewDTO(
        boolean activeStatus,
        String personID,
        String firstName,
        String lastName,
        LocalDate dateOfBirth,
        String fullPostalAddress,

        LocalDateTime dateOfClassStart,
        String sectionId,
        int amountOfTextbooks,

        int pendingFees,
        int homeworkLeaderBoardScore,

        String phoneNumber,
        String whatsappNumber
) {
}
