package com.eriks.growth.service;

import com.eriks.growth.Exercise;
import com.eriks.growth.Workout;
import com.eriks.growth.dao.ExerciseDao;
import com.eriks.growth.dao.WorkoutDao;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class WorkoutService {

    private final WorkoutDao workoutDao;
    private final ExerciseDao exerciseDao;

    public WorkoutService(WorkoutDao workoutDao, ExerciseDao exerciseDao) {
        this.workoutDao = workoutDao;
        this.exerciseDao = exerciseDao;
    }

    public List<Workout> getAllWorkouts() {
        return workoutDao.findAll();
    }

    public Workout getWorkoutByDate(Date date) {
        return workoutDao.getWorkoutByDate(date);
    }

    public void addWorkout(Workout workout) {
        workoutDao.save(workout);
    }

    public void addExerciseToWorkout(Date date, Exercise exercise) {
        Workout workout = getWorkoutByDate(date);
        if (workout == null) {
            workout = new Workout(date);
        }
        workout.addExercise(exercise);
        workoutDao.save(workout);
    }

    public void deleteExercise(UUID exerciseUuid) {
        Exercise exerciseToDelete = exerciseDao.getByUuid(exerciseUuid);
        exerciseToDelete.getWorkout().deleteExercise(exerciseToDelete);
        exerciseDao.delete(exerciseToDelete);
    }

    public void updateExerciseForWorkout(Date date, Exercise exercise) {
        exercise.getWorkout().updateExercise(exercise);
//        Workout workout = getWorkoutByDate(date);
//        if (workout == null) {
//            workout = new Workout(date);
//        }
//        Exercise oldExercise = exerciseDao.getByUuid(exercise.getUuid());
    }
}
