package com.eriks.growth.dao;

import com.eriks.growth.domain.ExerciseName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExerciseNameDao extends JpaRepository<ExerciseName, Long> {

    public boolean existsByNameIgnoreCase(String name);

    public List<ExerciseName> findAll();

    ExerciseName getByNameIgnoreCase(String name);
}
