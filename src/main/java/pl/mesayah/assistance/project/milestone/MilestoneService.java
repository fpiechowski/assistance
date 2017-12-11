package pl.mesayah.assistance.project.milestone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mesayah.assistance.domain.Milestone;
import pl.mesayah.assistance.domain.Project;
import pl.mesayah.assistance.repository.MilestoneRepository;

import java.util.List;

/**
 * @author Filip Piechowski
 */

/**
 * Service for managing milestone.
 */
@Service
public class MilestoneService {

    /**
     * Repository for fetching milestone from the database.
     */
    @Autowired
    private MilestoneRepository milestoneRepository;

    /**
     * Constructs a default milestone service. Needed for JPA to work.
     */
    public MilestoneService() {

    }

    /**
     * Saves given milestone in the database.
     *
     * @param milestone a milestone to save
     */
    public void save(Milestone milestone) {

        milestoneRepository.save(milestone);
    }

    /**
     * Finds one milestone by unique identifier.
     *
     * @param id an unique identifier of the project looked for
     * @return a milestone with given ID or null if not found
     */
    public Milestone findById(Long id) {

        return milestoneRepository.findOne(id);
    }

    /**
     * Finds all milestone for given project.
     *
     * @param project a project whose milestone we look for
     * @return all milestone of given project
     */
    public List<Milestone> findByProject(Project project) {

        return milestoneRepository.findByProject(project);
    }

    /**
     * Deletes milestone with given ID.
     *
     * @param id an id of the milestone to delete
     */
    public void delete(Long id) {

        milestoneRepository.delete(id);
    }

    /**
     * Deletes given milestone from the database.
     *
     * @param milestone a milestone to delete
     */
    public void delete(Milestone milestone) {

        milestoneRepository.delete(milestone);
    }
}
