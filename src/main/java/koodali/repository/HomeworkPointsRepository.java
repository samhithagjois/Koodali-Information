package koodali.repository;

import koodali.model.HomeworkPointsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeworkPointsRepository extends JpaRepository<HomeworkPointsEntity, Integer> {
}
