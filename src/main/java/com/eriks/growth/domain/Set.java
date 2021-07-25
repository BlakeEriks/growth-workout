package com.eriks.growth.domain;

import javax.persistence.*;
import java.util.UUID;

import static javax.persistence.GenerationType.IDENTITY;

@Entity(name = "Set")
@Table(name = "set")
public class Set {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", updatable = false)
    private long id;
    @Column(name="uuid", updatable = false, nullable = false)
    private UUID uuid;
    @Column(name = "weight", nullable = false)
    private double weight;
    @Column(name = "reps", nullable = false)
    private double reps;
    @Column(name = "is_pr", nullable = false)
    private boolean isPr;

    public Set(UUID uuid, double weight, double reps) {
        this.uuid = uuid;
        this.weight = weight;
        this.reps = reps;
    }

    public Set() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public double getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public boolean isPr() {
        return isPr;
    }

    public void setPr(boolean pr) {
        isPr = pr;
    }

    public double getVolume() {
        return reps * weight;
    }

}
