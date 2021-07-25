package com.eriks.growth.controller;

import com.eriks.growth.Util.StringUtils;
import com.eriks.growth.domain.Exercise;
import com.eriks.growth.domain.ExerciseName;
import com.eriks.growth.domain.Set;
import com.eriks.growth.service.ExerciseNameService;
import com.eriks.growth.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/exercise")
@CrossOrigin("*")
public class ExerciseController {

    private final ExerciseService exerciseService;
    private final ExerciseNameService exerciseNameService;

    @Autowired
    public ExerciseController(ExerciseService exerciseService, ExerciseNameService exerciseNameService) {
        this.exerciseService = exerciseService;
        this.exerciseNameService = exerciseNameService;
    }

    @DeleteMapping(
            path = "{exerciseUuid}"
    )
    public void delete(@PathVariable("exerciseUuid")UUID exerciseUuid) {
        exerciseService.deleteByUuid(exerciseUuid);
    }

    @GetMapping("names")
    public List<ExerciseName> getAllNames() {
        return exerciseNameService.getAll();
    }

    @GetMapping()
    public List<Exercise> getAll() {
        return exerciseService.getAll();
    }

    @GetMapping("{name}")
    public List<Exercise> getByName(@PathVariable("name") String name) {
        name = StringUtils.underscoresToSpaces(name);
        return exerciseService.getByName(name);
    }

    @GetMapping("{name}/pr")
    public List<Set> getPrMap(@PathVariable("name") String name) {
        name = StringUtils.underscoresToSpaces(name);
        return exerciseService.getPrSetList(name);
    }

    @PostMapping(
            path = "{exerciseUuid}/sets",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void addSet(@PathVariable("exerciseUuid") UUID exerciseUuid, @RequestBody Set set) {
        exerciseService.addSet(exerciseUuid, set);
    }

    @DeleteMapping(
            path = "{exerciseUuid}/sets/{setUuid}"
    )
    public void deleteSet(@PathVariable("exerciseUuid")UUID exerciseUuid, @PathVariable("setUuid")UUID setUuid) {
        exerciseService.deleteSet(exerciseUuid, setUuid);
    }

}
