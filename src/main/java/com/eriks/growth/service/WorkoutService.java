package com.eriks.growth.service;

import com.eriks.growth.dao.ExerciseDao;
import com.eriks.growth.dao.SetDao;
import com.eriks.growth.dao.WorkoutDao;
import com.eriks.growth.domain.Exercise;
import com.eriks.growth.domain.Workout;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class WorkoutService {

    private final WorkoutDao workoutDao;
    private final ExerciseService exerciseService;

    public WorkoutService(WorkoutDao workoutDao, ExerciseDao exerciseDao, SetDao setDao, ExerciseService exerciseService) {
        this.workoutDao = workoutDao;
        this.exerciseService = exerciseService;
    }

    public List<Workout> getAll() {
        return workoutDao.findAll();
    }

    public Workout getByDate(Date date) {
        return workoutDao.getWorkoutByDate(date);
    }

    public Workout save(Workout workout) {
        return workoutDao.save(workout);
    }

    public void saveAll(List<Workout> workouts) {
        workoutDao.saveAll(workouts);
    }

    public void addExerciseToWorkout(Date date, Exercise exercise) {

    }

    public void addExercise(Date date, Exercise exercise) {
        Workout workout = getByDate(date);
        if (workout == null) {
            workout = new Workout(date);
        }
        exercise = exerciseService.save(exercise);
        workout.addExercise(exercise);
        workoutDao.save(workout);
    }

    public List<Workout> getByDateRange(Date startDate, Date endDate) {
        return workoutDao.findByDateBetween(startDate, endDate);
    }
}
