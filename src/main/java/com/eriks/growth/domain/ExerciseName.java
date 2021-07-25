package com.eriks.growth.domain;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity(name = "ExerciseName")
@Table(name = "exercise_name")
public class ExerciseName {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name="name", updatable = false, nullable = false)
    private String name;

    public ExerciseName(String name) {
        this.name = name;
    }

    public ExerciseName() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
