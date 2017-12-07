package com.github.mesayah.assistance.repository;

import com.github.mesayah.assistance.model.Milestone;
import com.github.mesayah.assistance.model.Project;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MilestoneRepository extends CrudRepository<Milestone, Long> {

    List<Milestone> findByProject(Project project);
}
