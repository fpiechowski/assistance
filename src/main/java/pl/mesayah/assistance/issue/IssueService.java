package pl.mesayah.assistance.issue;
// TODO: Implement this

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mesayah.assistance.user.User;

import java.util.Collection;
import java.util.List;

/**
 * Service for reporting {@link Issue}s by clients.
 */
@Service
public class IssueService {

    /**
     * A repository for fetching issues from the database.
     */
    @Autowired
    private IssueRepository issueRepository;


    /**
     * Constructs a default issue object with no parameters.
     */
    public IssueService() {

    }


    /**
     * Saves a given issue to the repository.
     *
     * @param issue an issue to save
     * @return a saved issue object
     */
    public Issue save(Issue issue) {

        return issueRepository.save(issue);
    }


    /**
     * Finds an issue with given ID in the repository.
     *
     * @param id an ID of an issue to find
     * @return an issue with a given ID
     */
    public Issue findById(long id) {

        return issueRepository.findOne(id);
    }


    /**
     * Finds all issues reported by a given {@link User}.
     *
     * @param reportingUser a user who reported issues to find
     * @return a list of issues reported by a given user
     */
    public List<Issue> findByReportingUser(User reportingUser) {

        return issueRepository.findByReportingUser(reportingUser);
    }


    /**
     * Deletes an issue with a given ID from the repository.
     *
     * @param id an ID of an issue to delete
     */
    public void delete(long id) {

        issueRepository.delete(id);
    }


    /**
     * Deletes a given issue from the repository.
     *
     * @param issue an issue to delete
     */
    public void delete(Issue issue) {

        issueRepository.delete(issue);
    }


    public Collection<Issue> findAll() {

        return (Collection<Issue>) issueRepository.findAll();
    }
}
