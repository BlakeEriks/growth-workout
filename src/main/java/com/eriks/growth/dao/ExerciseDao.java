package com.eriks.growth.dao;

import com.eriks.growth.domain.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ExerciseDao extends JpaRepository<Exercise, Long> {

    Exercise getByUuid(UUID exerciseUuid);

    List<Exercise> getByName(String name);
}
