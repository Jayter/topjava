package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.util.List;

/**
 * GKislin
 * 15.06.2015.
 */
public interface MealService {
    Meal save(Meal meal, int userId);
    Meal update(Meal meal, int userId);
    Meal get(int mealId, int userId);
    void delete(int mealId, int userId);
    void deleteAll(int userId);
    List<Meal> getAll(int userId);
    List<Meal> getSortedByDate (LocalDate startTime, LocalDate endTime, int userId);
}
