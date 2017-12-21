package pl.mesayah.assistance.issue;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.mesayah.assistance.user.User;

import java.util.List;

@Repository
public interface IssueRepository extends CrudRepository<Issue, Long> {

    List<Issue> findByReportingUser(User reportingUser);
}
