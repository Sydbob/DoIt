package com.doIt.DoIt.dao;

import com.doIt.DoIt.entity.Project;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRepository extends CrudRepository<Project, Integer> {
}
