package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by Jayton on 09.09.2016.
 */
public class MealServlet extends HttpServlet{
    private static final Logger LOG = getLogger(UserServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("redirect to mealList");
        List<MealWithExceed> mealWithExceeds = MealsUtil.getMealsWithExceed();

        request.setAttribute("listMeals", mealWithExceeds);

        request.getRequestDispatcher("mealList.jsp").forward(request, response);
    }
}
