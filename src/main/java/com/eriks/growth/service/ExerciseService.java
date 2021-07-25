package com.eriks.growth.service;

import com.eriks.growth.dao.ExerciseDao;
import com.eriks.growth.dao.SetDao;
import com.eriks.growth.domain.Exercise;
import com.eriks.growth.domain.Set;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
public class ExerciseService {

    private final ExerciseNameService exerciseNameService;
    private final SetService setService;

    private final ExerciseDao exerciseDao;

    public ExerciseService(ExerciseDao exerciseDao, ExerciseNameService exerciseNameService, SetDao setDao, SetService setService) {
        this.exerciseDao = exerciseDao;
        this.exerciseNameService = exerciseNameService;
        this.setService = setService;
    }

    public Exercise save(Exercise exercise) {
        if (!exerciseNameService.exists(exercise.getName())) {
            System.out.println("Exercise name ~" + exercise.getName() + "~ does not exist!");
            return null;
        }
        return exerciseDao.save(exercise);
    }

    public List<Exercise> getByName(String name) {
        return exerciseDao.getByName(name);
    }

    public void addSet(UUID exerciseUuid, Set set) {
        Exercise exercise = exerciseDao.getByUuid(exerciseUuid);
        exercise.addSet(set);
        exerciseDao.save(exercise);
    }

    public void deleteSet(UUID exerciseUuid, UUID setUuid) {
        Exercise exercise = exerciseDao.getByUuid(exerciseUuid);
        exercise.getSets().removeIf(set -> set.getUuid().equals(setUuid));
        exerciseDao.save(exercise);
        setService.deleteByUuid(setUuid);
    }

    public void deleteByUuid(UUID exerciseUuid) {
        Exercise exerciseDelete =  exerciseDao.getByUuid(exerciseUuid);
        exerciseDelete.getWorkout().deleteExercise(exerciseDelete);
        exerciseDao.delete(exerciseDelete);
    }

    public List<Exercise> getAll() {
        return exerciseDao.findAll();
    }

    public List<Set> getPrSetList(String name) {
        List<Exercise> exercises = exerciseDao.getByName(name);
        List<Set> sets = new ArrayList<>();
        for (Exercise exercise : exercises) {
            sets.addAll(exercise.getSets());
        }
        List<Set> prSetList = new ArrayList<>();

        for (Set set : sets) {
            if (prSetList.isEmpty() || prSetList.get(prSetList.size()-1).getWeight() < set.getWeight()) {
                prSetList.add(set);
                prSetList.removeIf(oldPrSet -> oldPrSet.getWeight() < set.getWeight() && oldPrSet.getReps() <= set.getReps());
                continue;
            }
            for (int i = 0; i < prSetList.size(); i++) {
                Set prSet = prSetList.get(i);
                if (prSet.getWeight() >= set.getWeight()) {
                    if (prSet.getReps() < set.getReps()) {
                        if (prSet.getWeight() == set.getWeight()) prSet = set;
                        else prSetList.add(i, set);
                        prSetList.removeIf(oldPrSet -> oldPrSet.getWeight() < set.getWeight() && oldPrSet.getReps() <= set.getReps());
                    }
                    break;
                }
            }
        }
        return prSetList;
    }

    public boolean isPr(Exercise curExercise, Set set) {
        List<Set> prSetList = getPrSetList(curExercise.getName());

        return (prSetList.isEmpty()) ||
               (prSetList.stream().max(Comparator.comparing(Set::getWeight)).get().getWeight() < set.getWeight()) ||
               (prSetList.stream().anyMatch(prSet -> prSet.getWeight() <= set.getWeight() && prSet.getReps() < set.getReps()));

    }
}
