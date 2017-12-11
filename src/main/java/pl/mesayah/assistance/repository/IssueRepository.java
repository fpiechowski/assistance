package pl.mesayah.assistance.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.mesayah.assistance.domain.Issue;
import pl.mesayah.assistance.domain.User;

import java.util.List;

@Repository
public interface IssueRepository extends CrudRepository<Issue, Long> {

    List<Issue> findByReportingUser(User reportingUser);
}
