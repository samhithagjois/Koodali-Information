package koodali.model.dto.teacherDTO;

public record CreateTeacherDTO(String firstName,
                               String lastName,
                               String city,
                               String pinCode,
                               String fullPostalAddress,
                               String country,
                               String phoneNumber,
                               String whatsappNumber,
                               String sections) {
    //format : MU_INT_3;MU_BEG;ER_ADV
}
