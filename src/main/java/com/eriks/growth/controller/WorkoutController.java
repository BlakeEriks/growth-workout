package com.eriks.growth.controller;

import com.eriks.growth.domain.Exercise;
import com.eriks.growth.domain.Workout;
import com.eriks.growth.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

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
    public List<Workout> getAll() {
        return workoutService.getAll();
    }

    @GetMapping("{date}")
    public Workout getByDate(@PathVariable("date") @DateTimeFormat(pattern = "MM-dd-yyyy") Date date) {
        return workoutService.getByDate(date);
    }

    @GetMapping("{startDate}{endDate}")
    public List<Workout> getByDateRange(@PathVariable("startDate") @DateTimeFormat(pattern = "MM-dd-yyyy") Date startDate,
                                        @PathVariable("endDate") @DateTimeFormat(pattern = "MM-dd-yyyy") Date endDate) {
        return workoutService.getByDateRange(startDate, endDate);
    }

    @PostMapping(
        path = "add",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void add(@RequestBody Workout workout) {
        workoutService.save(workout);
    }

    @PostMapping(
        path = "{date}/exercises",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void addExercise(@PathVariable("date") @DateTimeFormat(pattern = "MM-dd-yyyy") Date date, @RequestBody Exercise exercise) {
        workoutService.addExercise(date, exercise);
    }

    @GetMapping("{date}/exercises")
    public List<Exercise> getExercises(@PathVariable("date") @DateTimeFormat(pattern = "MM-dd-yyyy") Date date) {
        return workoutService.getByDate(date).getExercises();
    }

}
