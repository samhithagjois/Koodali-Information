import koodali.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class ServiceTest {
//TODO!
    static List<LocalDateTime> schedule = new ArrayList<>();
    static Administrator samhitha = new Administrator("NKS_AD_001", "Samhitha", "Jois");
    static Administrator vinayak = new Administrator("NKS_AD_002", "Vinayak", "Belavadi");

    Student sushruth = new Student();

    Student riddhi = new Student();
    Student dhriti = new Student();
    Student parineeta = new Student();
    Student shravya = new Student();
    Section mu_adv = new Section("MU_ADV");
    Section mu_beg = new Section("MU_BEG");
    Section in_beg = new Section("IN_BEG");
    Teacher savitri = new Teacher("NKS_T_001", "Savitri", "", List.of("MU_BEG"));
    Teacher anita = new Teacher("NKS_T_002", "Anita", "", List.of("MU_ADV"));
    Teacher manasi = new Teacher("NKS_T_003", "Manasi", "", List.of("UNASSIGNED"));

    @BeforeEach
    public void fillUpClassAndStudentSchedule() {

        int[] days = {1, 7, 14, 21, 28};

        for (int month = 1; month <= 12; month++) {
            for (int day : days) {
                schedule.add(LocalDateTime.of(2025, month, day, 8, 30));
            }
        }



        samhitha.addPermission(AdminPermissions.MU);
        vinayak.addPermission(AdminPermissions.DE);
        vinayak.addPermission(AdminPermissions.IN);


    }



   /* @Test
    public void adminPermissions() {
        assertEquals(AdminPermissions.MU, samhitha.getPermissions().get(0));
        assertTrue(adminOperationService.checkPermissionToModify(samhitha, mu_adv));
        assertDoesNotThrow(IllegalAdminActionException::new);

    }

    @Test
    public void addPeopleListToSystem() {

        Person s = adminOperationService.addPersonToSystem(sushruth);
        assertEquals("Student", s.getClass().getSimpleName());
        assertEquals(sushruth.getID(), s.getID());

        List<Person> people = List.of(riddhi, dhriti, parineeta, shravya, manasi, savitri, anita, vinayak, samhitha);
        adminOperationService.addListOfPersonsToSystem(people);
        assertArrayEquals(people.toArray(), adminOperationService.addListOfPersonsToSystem(people).toArray());
        assertDoesNotThrow(DuplicatePersonException::new);

    }

    @Test
    public void attendanceQuote() {
        Map<LocalDateTime, Boolean> sushruthSchedule = new HashMap<>();
        int[] attendance = {1, 7, 21};

        for (int month = 1; month <= 12; month++) {
            for (int day : attendance) {
                sushruthSchedule.put(LocalDateTime.of(2025, month, day, 8, 30), true);
            }
        }

        Map<LocalDateTime, Boolean> parineetaSchedule = new HashMap<>();
        int[] attendance1 = {1, 21};

        for (int month = 1; month <= 12; month++) {
            for (int day : attendance1) {
                parineetaSchedule.put(LocalDateTime.of(2025, month, day, 8, 30), true);
            }
        }


    }*/
}
