package com.github.mesayah.assistance.todo;

import com.github.mesayah.assistance.model.PersonalTask;
import com.github.mesayah.assistance.model.User;
import com.github.mesayah.assistance.repository.PersonalTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Filip Piechowski
 */

/**
 * Service for managing personal tasks. It creates new personal tasks, modifies and deletes them. All business logic
 * related with Personal Task module lays here.
 */
@Service
public class PersonalTaskService {

    /**
     * Repository for fetching personal tasks from the database.
     */
    @Autowired
    private PersonalTaskRepository personalTaskRepository;

    /**
     * Constructs a default milestone service. Needed for JPA to work.
     */
    public PersonalTaskService() {

    }

    /**
     * Saves a personal task in the database.
     *
     * @param personalTask a personal task to save
     */
    public void save(PersonalTask personalTask) {

        personalTaskRepository.save(personalTask);
    }

    /**
     * Finds personal task with given ID.
     *
     * @param id a primary key of the personal task looked for
     * @return a personal task with given ID
     */
    public PersonalTask findById(Long id) {

        return personalTaskRepository.findOne(id);
    }

    /**
     * Finds all personal task that given user owns. Yes, I was high when I coded it.
     *
     * @param owner a user whose personal task to look for
     * @return all personal tasks of given user
     */
    public List<PersonalTask> findByOwner(User owner) {

        return personalTaskRepository.findByOwner(owner);
    }

    /**
     * Deletes a personal task with given ID.
     *
     * @param id an identifier of the task to be deleted
     */
    public void delete(Long id) {

        personalTaskRepository.delete(id);
    }

    /**
     * Deletes given personal task from the database.
     *
     * @param personalTask a personal task to delete from the database
     */
    public void delete(PersonalTask personalTask) {

        personalTaskRepository.delete(personalTask);
    }
}