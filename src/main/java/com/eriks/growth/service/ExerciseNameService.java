package com.eriks.growth.service;

import com.eriks.growth.dao.ExerciseNameDao;
import com.eriks.growth.domain.ExerciseName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseNameService {

    private final ExerciseNameDao exerciseNameDao;

    @Autowired
    public ExerciseNameService(ExerciseNameDao exerciseDetailsDao) {
        this.exerciseNameDao = exerciseDetailsDao;
    }

    public List<ExerciseName> getAll() {
        return exerciseNameDao.findAll();
    }

    public ExerciseName getByName(String name) {
        return exerciseNameDao.getByNameIgnoreCase(name);
    }

    public void add(String exerciseName) {
        exerciseNameDao.save(new ExerciseName(exerciseName));
    }

    public boolean exists(String exerciseName) {
        return exerciseNameDao.existsByNameIgnoreCase(exerciseName);
    }
}
