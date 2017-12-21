package pl.mesayah.assistance.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mesayah.assistance.user.User;

import java.util.List;

/**
 * @author Filip Piechowski
 */

/**
 * Service for managing personal task. It creates new personal task, modifies and deletes them. All business logic
 * related with Personal Task module lays here.
 */
@Service
public class PersonalNoteService {

    /**
     * Repository for fetching personal task from the database.
     */
    @Autowired
    private PersonalNoteRepository personalNoteRepository;

    /**
     * Constructs a default milestone service. Needed for JPA to work.
     */
    public PersonalNoteService() {

    }

    /**
     * Saves a personal task in the database.
     *
     * @param personalNote a personal task to save
     */
    public void save(PersonalNote personalNote) {

        personalNoteRepository.save(personalNote);
    }

    /**
     * Finds personal task with given ID.
     *
     * @param id a primary key of the personal task looked for
     * @return a personal task with given ID
     */
    public PersonalNote findById(Long id) {

        return personalNoteRepository.findOne(id);
    }

    /**
     * Finds all personal task that given user owns. Yes, I was high when I coded it.
     *
     * @param owner a user whose personal task to look for
     * @return all personal task of given user
     */
    public List<PersonalNote> findByOwner(User owner) {

        return personalNoteRepository.findByOwner(owner);
    }

    /**
     * Deletes a personal task with given ID.
     *
     * @param id an identifier of the task to be deleted
     */
    public void delete(Long id) {

        personalNoteRepository.delete(id);
    }

    /**
     * Deletes given personal task from the database.
     *
     * @param personalNote a personal task to delete from the database
     */
    public void delete(PersonalNote personalNote) {

        personalNoteRepository.delete(personalNote);
    }
}
