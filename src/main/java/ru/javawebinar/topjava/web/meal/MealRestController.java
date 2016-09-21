package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
@Controller
public class MealRestController {

    @Autowired
    private MealService service;

    public Meal save(Meal meal) {
        int userId = AuthorizedUser.id();
        return service.save(meal, userId);
    }

    public void update(Meal meal) {
        int userId = AuthorizedUser.id();
        service.update(meal, userId);
    }

    public Meal get(int id) {
        int userId = AuthorizedUser.id();
        return service.get(id, userId);
    }
    public void delete(int id) {
        int userId = AuthorizedUser.id();
        service.delete(id, userId);
    }

    public List<MealWithExceed> getAll() {
        int userId = AuthorizedUser.id();
        return MealsUtil.getFilteredWithExceeded(service.getAll(userId),
                LocalTime.MIN, LocalTime.MAX, AuthorizedUser.getCaloriesPerDay());
    }

    public List<MealWithExceed> getSorted(LocalDate startDate, LocalTime starTime, LocalDate endDate, LocalTime endTime) {
        int userId = AuthorizedUser.id();
        return MealsUtil.getFilteredWithExceeded(service.getSortedByDate(startDate, endDate, userId),
                starTime, endTime, AuthorizedUser.getCaloriesPerDay());
    }

    public void deleteAll() {
        int userId = AuthorizedUser.id();
        service.deleteAll(userId);
    }

}
