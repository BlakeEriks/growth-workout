package com.eriks.growth.dao;

import com.eriks.growth.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface WorkoutDao extends JpaRepository<Workout, Long> {

    Workout getWorkoutByDate(Date date);

}
