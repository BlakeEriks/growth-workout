package com.eriks.growth.dao;

import com.eriks.growth.domain.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface WorkoutDao extends JpaRepository<Workout, Long> {

    Workout getWorkoutByDate(Date date);

    List<Workout> findByDateBetween(Date startDate, Date endDate);

}
