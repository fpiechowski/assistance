package pl.mesayah.assistance.todo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.mesayah.assistance.user.User;

import java.util.List;

/**
 * @author Filip Piechowski
 */

/**
 * Repository for fetching personal task from database. It handles database communication and provides specified
 * methods for custom queries.
 */
@Repository
public interface PersonalNoteRepository extends CrudRepository<PersonalNote, Long> {

    /**
     * Finds and returns all personal task owned by a given user.
     *
     * @param owner a user whose personal task to look for
     * @return a list of all personal task owned by a given user
     */
    List<PersonalNote> findByOwner(User owner);
}
