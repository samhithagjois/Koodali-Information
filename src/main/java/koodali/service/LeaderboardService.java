package koodali.service;

import koodali.model.Student;
import koodali.repository.LeaderboardRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LeaderboardService {

    private final LeaderboardRepository leaderboardRepository;
    private final StudentService studentService;

    //HomeworkPointsService service

    public LeaderboardService(LeaderboardRepository leaderboardRepository, StudentService studentService) {

        this.leaderboardRepository = leaderboardRepository;
        this.studentService = studentService;
    }

    public List<Student> fetchAllStudentsByIDs() {
        List<Student> result = new ArrayList<>();
        List<String> ids = leaderboardRepository.findAll().stream().map(Student::getID).toList();
        for (String id : ids) {
            result.add(studentService.findByID(id));
        }
        return result;
    }

    public List<Student> sortBy(LeaderboardOption option) {

        return List.of();
    }

    public List<Student> filterBy(LeaderboardOption option) {

        return List.of();
    }

    public void updateLeaderboard() {

    }


}
