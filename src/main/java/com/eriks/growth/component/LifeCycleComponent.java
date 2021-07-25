package com.eriks.growth.component;

import com.eriks.growth.dao.SetDao;
import com.eriks.growth.service.ExerciseNameService;
import com.eriks.growth.service.FileService;
import com.eriks.growth.service.WorkoutService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class LifeCycleComponent {

    private final ExerciseNameService exerciseNameService;
    private final FileService fileService;
    private final SetDao setDao;

    public LifeCycleComponent(WorkoutService workoutService, ExerciseNameService exerciseNameService, FileService fileService, SetDao setDao) {
        this.exerciseNameService = exerciseNameService;
        this.fileService = fileService;
        this.setDao = setDao;
    }

    @PostConstruct
    public void initializeData() {
        initializeExerciseNames();
        readInWorkoutsFromCsv();
    }

    public void initializeExerciseNames() {
        for (String name : ExerciseNames.getExerciseNames()) {
            exerciseNameService.add(name);
        }
    }

    public void readInWorkoutsFromCsv() {
        fileService.loadWorkoutsFromWorkoutCsv();
    }


}
