package koodali.repository;

import koodali.model.Teacher;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends PersonRepository<Teacher> {
}
