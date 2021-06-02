package com.eriks.growth;

import com.eriks.growth.dao.WorkoutDao;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(WorkoutDao workoutDao) {
		return args -> {
			Workout w = new Workout(), w2 = new Workout();
			LocalDate today = LocalDate.of(2021,5,12);

			w.setDate(new Date(System.currentTimeMillis()));
			w2.setDate(new Date(System.currentTimeMillis()));

			w.setWorkoutType(WorkoutType.PUSH_A);
			w2.setWorkoutType(WorkoutType.PULL_A);

			Exercise e1 = new Exercise(), e2 = new Exercise();

			e1.setName("Bench Press");
			e2.setName("Deadlift");

			e1.setUuid(UUID.randomUUID());
			e2.setUuid(UUID.randomUUID());

			Set s1 = new Set(), s2 = new Set(), s3 = new Set(), s4 = new Set();
			s1.setWeight(100);
			s1.setReps(10);
			s1.setUuid(UUID.randomUUID());
			s2.setWeight(225);
			s2.setReps(5);
			s2.setUuid(UUID.randomUUID());
			s3.setWeight(50);
			s3.setReps(20);
			s3.setUuid(UUID.randomUUID());
			s4.setWeight(100);
			s4.setReps(30);
			s4.setUuid(UUID.randomUUID());


			e1.addSet(s1);
			e1.addSet(s2);
			e2.addSet(s3);
			e2.addSet(s4);
			w.addExercise(e1);
			w.addExercise(e2);
			workoutDao.save(w);
			DateFormat dtf = new SimpleDateFormat("MM-dd-yyyy");

		};
	}

}
