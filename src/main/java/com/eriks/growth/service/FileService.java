package com.eriks.growth.service;

import com.eriks.growth.dao.SetDao;
import com.eriks.growth.domain.Exercise;
import com.eriks.growth.domain.ExerciseName;
import com.eriks.growth.domain.Set;
import com.eriks.growth.domain.Workout;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class FileService {

    private static final String filename = "persistence.json";
    private static final String csvFilename = "Lifts - Phase 1.csv";
    private static final String COMMA_DELIMITER = ",";
    private static final String SET_DELIMITER = "x";
    private static final String DATE_FORMAT = "MM/dd/yy";

    private final WorkoutService workoutService;
    private final ExerciseNameService exerciseNameService;
    private final ExerciseService exerciseService;
    private final SetService setService;
    private final ObjectMapper mapper;
    private final SetDao setDao;

    public FileService(WorkoutService workoutService, ExerciseNameService exerciseDetailsService, ExerciseService exerciseService, SetService setService, SetDao setDao) {
        this.workoutService = workoutService;
        this.exerciseNameService = exerciseDetailsService;
        this.exerciseService = exerciseService;
        this.setService = setService;
        this.setDao = setDao;
        this.mapper = new ObjectMapper();
    }

    public void writeToFile(List<Object> objects) {
        try {
            mapper.writeValue(Paths.get(filename).toFile(), objects);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Workout> getWorkoutsFromFile() {
        try {
            return Arrays.asList(mapper.readValue(Paths.get(filename).toFile(), Workout[].class));
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean loadWorkoutsFromWorkoutCsv() {

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilename))) {
            String line;
            boolean newWorkout = true;
            Workout curWorkout = null;
            List<Exercise> curExercises = new ArrayList<>();

            //Loop lines
            while ((line = br.readLine()) != null) {
                String[] values = line.split(COMMA_DELIMITER);
                if (values.length == 0) {
                    workoutService.save(curWorkout);
                    curExercises.clear();
                    newWorkout = true;
                    continue;
                }

                //Loop values in single line
                for (int i = 0; i < values.length; i++) {
                    if (values[i] == null || values[i].isEmpty()) {
                        continue;
                    }
                    if (newWorkout) {
                        if (i == 0) {
                            curWorkout = workoutService.save(new Workout(new SimpleDateFormat(DATE_FORMAT).parse(values[i])));
                        }
                        else {
                            ExerciseName name = exerciseNameService.getByName(values[i]);
                            Exercise curExercise = exerciseService.save(new Exercise(UUID.randomUUID(), name.getName()));
                            curWorkout.addExercise(curExercise);
                            curExercises.add(curExercise);
                        }
                    }
                    else {
                        String[] set = values[i].split(SET_DELIMITER);
                        Exercise curExercise = curExercises.get(i-1);
                        Set curSet = setService.add(new Set(UUID.randomUUID(), Double.parseDouble(set[0]), Double.parseDouble(set[1])));
                        curSet.setPr(exerciseService.isPr(curExercise, curSet));
                        curExercise.addSet(curSet);
                        exerciseService.save(curExercise);
                    }
                }
                if (newWorkout) newWorkout = false;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
