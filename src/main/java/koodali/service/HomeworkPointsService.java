package koodali.service;

import koodali.model.HomeworkPointsEntity;
import koodali.model.Teacher;
import koodali.repository.HomeworkPointsRepository;
import koodali.service.exceptions.StudentNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class HomeworkPointsService {

    private final HomeworkPointsRepository homeworkPointsRepository;

    private final HomeworkPointsExcelService homeworkPointsExcelService;

    private final SectionService sectionService;

    private final TeacherService teacherService;


    public HomeworkPointsService(HomeworkPointsRepository homeworkPointsRepository,
                                 HomeworkPointsExcelService homeworkPointsExcelService,
                                 SectionService sectionService,
                                 TeacherService teacherService) {
        this.homeworkPointsRepository = homeworkPointsRepository;
        this.homeworkPointsExcelService = homeworkPointsExcelService;
        this.sectionService = sectionService;

        this.teacherService = teacherService;
    }

    public List<HomeworkPointsEntity> getListForSection(String sectionID) {
        List<HomeworkPointsEntity> result = new ArrayList<>();

        for (HomeworkPointsEntity entity : homeworkPointsRepository.findAll()) {

            String section = sectionService.getSectionByName(sectionID).getName();
            if (section.equalsIgnoreCase(sectionID)) {
                result.add(entity);
            }

        }
        return result;
    }

    public HomeworkPointsEntity updateCell(String studentID, LocalDate week, int points) {
        HomeworkPointsEntity entity = homeworkPointsRepository
                .findAll()
                .stream()
                .filter(h -> h.getStudentID().equalsIgnoreCase(studentID))
                .filter(h -> h.getWeek().isEqual(week))
                .findFirst()
                .orElseThrow(StudentNotFoundException::new);

        entity.setPoints(points);

        return entity;
    }

    public List<HomeworkPointsEntity> getListForAllSectionsOfTeacher(String teacherID) {
        List<HomeworkPointsEntity> result = new ArrayList<>();
        Teacher teacher = teacherService.findByID(teacherID);
        for (String section : teacher.getSections()) {
            result.addAll(getListForSection(section));

        }

        return result;
    }

    //HomeworkPointsService
    // : methods :
    // 1. getListForSection(String sectionID)
    // 2. updateCell(String studentID,LocalDate week, int points)
    // 3. getListForAllSectionsOfTeacher(String teacherID)
    // 4. clearCell(HomeworkPointsEntity entity)
    // 5. ...


}

