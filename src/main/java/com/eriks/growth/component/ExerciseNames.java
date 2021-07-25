package com.eriks.growth.component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface ExerciseNames {

    /* PUSH */
    String OVERHEAD_PRESS = "Overhead Press";
    String INCLINE_BENCH_PRESS = "Incline Bench Press";
    String TRICEP_PUSH_DOWN = "Tricep Push Down";
    String CABLE_CROSSOVER = "Cable Crossover";
    String UPWARD_CABLE_CROSSOVER = "Upward Cable Crossover";
    String BENCH_PRESS = "Bench Press";
    String DUMBBELL_SHOULDER_PRESS = "Dumbbell Shoulder Press";
    String TRICEP_DIP = "Tricep Dip";
    String MACHINE_CHEST_FLY = "Machine Chest Fly";
    String OVERHEAD_CABLE_EXTENSION = "Overhead Cable Extension";
    String LATERAL_RAISE = "Lateral Raise";
    String MACHINE_SHOULDER_PRESS = "Machine Shoulder Press";
    String SINGLE_CABLE_EXTENSION = "Single Cable Extension";
    String CLOSE_GRIP_DUMBBELL_PRESS = "Close Grip Dumbbell Press";
    String MACHINE_TRICEP_EXTENSION = "Machine Tricep Extension";
    String DUMBBELL_TRICEP_EXTENSION = "Dumbbell Tricep Extension";
    String DUMBBELL_INCLINE_BENCH_PRESS = "Dumbbell Incline Bench Press";
    String CABLE_LATERAL_RAISE = "Cable Lateral Raise";
    String TRICEP_ROPE_PUSH_DOWN = "Tricep Rope Push Down";

    /* PULL */
    String DEADLIFT = "Deadlift";
    String BARBELL_ROW = "Barbell Row";
    String PULL_UP = "Pull-Up";
    String DUMBBELL_ROW = "Dumbbell Row";
    String WIDE_GRIP_PULL_DOWN = "Wide Grip Pull Down";
    String BARBELL_BICEP_CURL = "Barbell Bicep Curl";
    String MACHINE_PREACHER_CURL = "Machine Preacher Curl";
    String CABLE_ROW = "Cable Row";
    String BARBELL_SHRUG = "Barbell Shrug";
    String DUMBBELL_CURL = "Dumbbell Curl";
    String FACE_PULL = "Face Pull";
    String CABLE_BICEP_CURL = "Cable Bicep Curl";

    /* Legs */
    String BARBELL_SQUAT = "Barbell Squat";
    String DUMBBELL_SPLIT_SQUAT = "Dumbbell Split Squat";
    String ROMANIAN_DEADLIFT = "Romanian Deadlift";
    String CALF_RAISE = "Calf Raise";
    String LEG_EXTENSION = "Leg Extension";

    /* Abs */
    String CABLE_AB_CURL = "Cable Ab Curl";
    String HALF_LEG_RAISE = "Half Leg Raise";
    String FULL_LEG_RAISE = "Full Leg Raise";

    static List<String> getExerciseNames() {
        return new ArrayList<>(Arrays.asList(OVERHEAD_PRESS, INCLINE_BENCH_PRESS, TRICEP_PUSH_DOWN, CABLE_CROSSOVER,
                                             UPWARD_CABLE_CROSSOVER, BENCH_PRESS, DUMBBELL_SHOULDER_PRESS, TRICEP_DIP,
                                             MACHINE_CHEST_FLY, OVERHEAD_CABLE_EXTENSION, LATERAL_RAISE, MACHINE_SHOULDER_PRESS,
                                             SINGLE_CABLE_EXTENSION, CLOSE_GRIP_DUMBBELL_PRESS, MACHINE_TRICEP_EXTENSION,
                                             DUMBBELL_TRICEP_EXTENSION, DUMBBELL_INCLINE_BENCH_PRESS, CABLE_LATERAL_RAISE, TRICEP_ROPE_PUSH_DOWN,

                                             BARBELL_ROW, PULL_UP, DUMBBELL_ROW, WIDE_GRIP_PULL_DOWN, BARBELL_BICEP_CURL,
                                             MACHINE_PREACHER_CURL, CABLE_ROW, BARBELL_SHRUG, DUMBBELL_CURL, FACE_PULL,
                                             CABLE_BICEP_CURL, DEADLIFT,

                                             BARBELL_SQUAT, DUMBBELL_SPLIT_SQUAT, ROMANIAN_DEADLIFT, CALF_RAISE, LEG_EXTENSION,

                                             CABLE_AB_CURL, HALF_LEG_RAISE, FULL_LEG_RAISE
        ));
    }

}
