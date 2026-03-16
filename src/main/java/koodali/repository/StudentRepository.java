package koodali.repository;

import koodali.model.Student;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface StudentRepository extends PersonRepository<Student> {
    Optional<Student> findByFirstNameIgnoreCaseOrLastNameIgnoreCase(
            String firstName,
            String lastName
    );

}
