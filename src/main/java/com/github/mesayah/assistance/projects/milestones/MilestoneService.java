package com.github.mesayah.assistance.projects.milestones;

import com.github.mesayah.assistance.model.Milestone;
import com.github.mesayah.assistance.model.Project;
import com.github.mesayah.assistance.repository.MilestoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for managing milestones.
 */
@Service
public class MilestoneService {

    /**
     * Repository for fetching milestones from the database.
     */
    @Autowired
    private MilestoneRepository milestoneRepository;

    /**
     * Default constructor with no arguments.
     */
    public MilestoneService() {

    }

    /**
     * Saves given milestone in the database.
     *
     * @param milestone milestone to save
     */
    public void save(Milestone milestone) {

        milestoneRepository.save(milestone);
    }

    /**
     * Finds one milestone by unique identifier.
     *
     * @param id unique identifier of the project looked for
     * @return milestone with given ID or null if not found
     */
    public Milestone findById(Long id) {

        return milestoneRepository.findOne(id);
    }

    /**
     * Find all milestones for given project.
     *
     * @param project project whose milestones we look for
     * @return all milestones of given project
     */
    public List<Milestone> findByProject(Project project) {

        return milestoneRepository.findByProject(project);
    }

    /**
     * Deletes milestone with given ID.
     *
     * @param id id of the milestone to delete
     */
    public void delete(Long id) {

        milestoneRepository.delete(id);
    }

    /**
     * Deletes given milestone from the database.
     *
     * @param milestone milestone to delete
     */
    public void delete(Milestone milestone) {

        milestoneRepository.delete(milestone);
    }
}
