package com.eriks.growth.controller;

import com.eriks.growth.Exercise;
import com.eriks.growth.Workout;
import com.eriks.growth.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/workout")
@CrossOrigin("*")
public class WorkoutController {

    private final WorkoutService workoutService;

    @Autowired
    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @GetMapping
    public List<Workout> getAllWorkouts() {
        return workoutService.getAllWorkouts();
    }

    @GetMapping("{date}")
    public Workout getWorkoutByDate(@PathVariable("date") @DateTimeFormat(pattern = "MM-dd-yyyy") Date date) {
        return workoutService.getWorkoutByDate(date);
    }

    @PostMapping(
        path = "add",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void addWorkout(@RequestBody Workout workout) {
        workoutService.addWorkout(workout);
    }

    @PostMapping(
        path = "{date}/add-exercise",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void addExerciseToWorkout(@PathVariable("date") @DateTimeFormat(pattern = "MM-dd-yyyy") Date date, @RequestBody Exercise exercise) {
        workoutService.addExerciseToWorkout(date, exercise);
    }

    @DeleteMapping(
        path = "delete-exercise/{exerciseUuid}"
    )
    public void deleteExerciseFromWorkout(@PathVariable("exerciseUuid")UUID exerciseUuid) {
        workoutService.deleteExercise(exerciseUuid);
    }

    @PostMapping(
            path = "{date}/update-exercise",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void updateExerciseForWorkout(@PathVariable("date") @DateTimeFormat(pattern = "MM-dd-yyyy") Date date, @RequestBody Exercise exercise) {
        workoutService.updateExerciseForWorkout(date, exercise);
    }

}
