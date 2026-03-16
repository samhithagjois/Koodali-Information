package koodali.service;

import koodali.model.Person;
import koodali.repository.PersonRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public abstract class PersonService<T extends Person> {

    private final PersonRepository<T> personRepo;

    protected PersonService(PersonRepository<T> personRepo) {
        this.personRepo = personRepo;
    }

    public List<T> getAll() {
        return personRepo.findAll();
    }

    public T update(T person) {
        return personRepo.save(person);
    }

    public T save(T person) {
        return personRepo.save(person);
    }

    public T findByID(String id) {
        if (personRepo.findById(id).isPresent()) {
            return personRepo.findById(id).get();
        } else {
            return null;
        }
    }

    public T delete(String id) {
        personRepo.deleteById(id);
        return personRepo.getReferenceById(id);
    }

    public boolean contains(T p) {
        return personRepo.findAll().contains(p);
    }

    public void clear() {
        personRepo.deleteAll();
    }

}
