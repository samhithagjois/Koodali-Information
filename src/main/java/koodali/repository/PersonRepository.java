package koodali.repository;

import koodali.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface PersonRepository<T extends Person> extends JpaRepository<T, String> {
    Optional<T> findByFirstNameIgnoreCaseOrLastNameIgnoreCase(
            String firstName,
            String lastName
    );

}
