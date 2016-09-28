package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DbPopulator;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import static ru.javawebinar.topjava.MealTestData.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Jayton on 27.09.2016.
 */
@ContextConfiguration(
        {"classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"}
)
@RunWith(SpringJUnit4ClassRunner.class)
public class MealServiceTest {
    @Autowired
    MealService service;

    @Autowired
    DbPopulator dbPopulator;

    @Before
    public void setUp() throws Exception {
        dbPopulator.execute();
    }

    @Test
    public void testSave() throws Exception {
        Meal meal = new Meal(LocalDateTime.now(), "Food", 555);
        Meal saved = service.save(meal, SECOND_USER_ID);
        meal.setId(saved.getId());
        MATCHER.assertCollectionEquals(Arrays.asList(FIRST_MEAL, SECOND_MEAL, meal),
                service.getAll(SECOND_USER_ID));
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(FIRST_MEAL_ID, SECOND_USER_ID);
        MATCHER.assertCollectionEquals(Collections.singletonList(SECOND_MEAL),
                service.getAll(SECOND_USER_ID));
    }

    @Test
    public void testGet() throws Exception {
        Meal meal = service.get(THIRD_MEAL_ID, FIRST_USER_ID);
        MATCHER.assertEquals(meal, THIRD_MEAL);
    }

    @Test
    public void testGetAll() throws Exception {
        MATCHER.assertCollectionEquals(Arrays.asList(FIRST_MEAL, SECOND_MEAL),
                service.getAll(SECOND_USER_ID));
    }

    @Test
    public void testUpdate() throws Exception {
        Meal updated = new Meal(THIRD_MEAL);
        updated.setDescription("UPDATED_MEAL");
        updated.setCalories(1123);
        service.save(updated, FIRST_USER_ID);

        MATCHER.assertEquals(updated, service.get(THIRD_MEAL_ID, FIRST_USER_ID));
    }

    @Test
    public void updateForeign() throws Exception {
        Meal updated = new Meal(THIRD_MEAL);
        updated.setDescription("UPDATED_MEAL");
        updated.setCalories(1123);

        service.save(updated, -1);
    }

    @Test(expected = NotFoundException.class)
    public void getForeign() throws Exception {
        Meal meal = service.get(THIRD_MEAL_ID, SECOND_USER_ID);
    }

    @Test(expected = NotFoundException.class)
    public void deleteForeign() throws Exception {
        service.delete(THIRD_MEAL_ID, SECOND_USER_ID);
    }

}