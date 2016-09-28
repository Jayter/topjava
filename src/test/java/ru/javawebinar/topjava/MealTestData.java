package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.Objects;

import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;
/**
 * GKislin
 * 13.03.2015.
 */
public class MealTestData {
    public static final int FIRST_USER_ID = START_SEQ;
    public static final int SECOND_USER_ID = START_SEQ + 1;

    public static final int FIRST_MEAL_ID = START_SEQ + 2;
    public static final int SECOND_MEAL_ID = START_SEQ + 3;
    public static final int THIRD_MEAL_ID = START_SEQ + 4;

    public static final Meal FIRST_MEAL = new Meal(FIRST_MEAL_ID, LocalDateTime.now(), "SUPER_ADMIN_MEAL", 500);
    public static final Meal SECOND_MEAL = new Meal(SECOND_MEAL_ID, LocalDateTime.now(), "SUPER_ADMIN_MEAL_1", 1000);
    public static final Meal THIRD_MEAL = new Meal(THIRD_MEAL_ID, LocalDateTime.now(), "USER_MEAL", 400);


    public static final ModelMatcher<Meal> MATCHER = new ModelMatcher<>(
            ((expected, actual) -> expected == actual ||
                    (Objects.equals(expected.getId(), actual.getId()))
                            &&Objects.equals(expected.getDateTime().toLocalDate(), actual.getDateTime().toLocalDate())
                            &&Objects.equals(expected.getDescription(), actual.getDescription())
                            &&Objects.equals(expected.getId(), actual.getId()))
    );


}
