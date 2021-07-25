package com.eriks.growth.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.*;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;

@Entity(name = "Exercise")
@Table(name = "exercise")
public class Exercise {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;
    @Column(name="uuid", updatable = false, nullable = false)
    private UUID uuid;
    @Column(name="name", updatable = false, nullable = false)
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_workout")
    @JsonIgnore
    private Workout workout;

    @OneToMany(cascade = ALL, fetch = EAGER, orphanRemoval = true)
    @JoinColumn(name = "fk_exercise")
    private List<Set> sets;
    public Exercise(UUID uuid) {
        this.uuid = uuid;
    }

    public Exercise() {}

    public Exercise(UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }

    public Date getDate() {
        return workout.getDate();
    }

    public double getVolume() {
        return sets.stream().mapToDouble(Set::getVolume).sum();
    }

    public double getMaxWeight() {
        return sets.stream().max(Comparator.comparing(Set::getWeight)).get().getWeight();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Workout getWorkout() {
        return workout;
    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }

    public List<Set> getSets() {
        return sets;
    }

    public void setSets(List<Set> sets) {
        this.sets = sets;
    }

    public void addSet(Set set) {
        if (this.sets == null) {
            this.sets = new ArrayList<>();
        }
        this.sets.add(set);
    }
}
