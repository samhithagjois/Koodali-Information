package koodali.model.dto.studentDTO;

public record StudentFeesDTO(
        String personId,
        String firstName,
        String lastName,
        int feesPaid,
        int pendingFees
) {
}
