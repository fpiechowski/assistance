package com.github.mesayah.assistance.repository;

import com.github.mesayah.assistance.model.PersonalTask;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalTaskRepository extends CrudRepository<PersonalTask, Long> {


}
