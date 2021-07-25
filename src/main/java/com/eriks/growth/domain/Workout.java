package com.eriks.growth.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.IDENTITY;

@Entity(name = "Workout")
@Table(name = "workout")
public class Workout {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "date", nullable = false, unique = true)
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "MM-dd-yyyy", timezone="GMT-10:00")
    private Date date;

    @Column(name = "workout_type")
    private WorkoutType workoutType;

    @OneToMany(mappedBy = "workout", cascade = ALL, orphanRemoval = true)
    private List<Exercise> exercises;

    public Workout() {
        this.exercises = new ArrayList<>();
    }

    public Workout(Date date) {
        this.date = date;
        this.exercises = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getVolume() {
        return exercises.stream().mapToDouble(Exercise::getVolume).sum();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public WorkoutType getWorkoutType() {
        return workoutType;
    }

    public void setWorkoutType(WorkoutType workoutType) {
        this.workoutType = workoutType;
    }

    public List<Exercise> getExercises() {
        return this.exercises.stream().sorted(Comparator.comparing(Exercise::getId)).collect(Collectors.toList());
    }

    public void setExercises(List<Exercise> exercises) {
        exercises.forEach(exercise -> {
            this.exercises.add(exercise);
            exercise.setWorkout(this);
        });
    }

    public void addExercise(Exercise exercise) {
        this.exercises.add(exercise);
        exercise.setWorkout(this);
    }

    public void deleteExercise(Exercise exerciseToDelete) {
        this.exercises.removeIf(exercise -> exercise.getId() == exerciseToDelete.getId());
    }
}
