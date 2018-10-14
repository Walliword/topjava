package ru.javawebinar.topjava.web.meal;

import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.List;

import static ru.javawebinar.topjava.web.SecurityUtil.authUserCaloriesPerDay;
import static ru.javawebinar.topjava.web.SecurityUtil.authUserId;

@Controller
public class MealRestController extends AbstractMealRestController {

    public List<MealWithExceed> getAll() {
        List<Meal> meals = super.getAll(authUserId());
        return MealsUtil.getWithExceeded(meals, authUserCaloriesPerDay());
    }

    public Meal get(int id) {
        return super.get(id, authUserId());
    }

    public void delete(int id) {
        super.delete(id, authUserId());
    }

    @Override
    public Meal create(Meal meal) {
        return super.create(meal);
    }

    @Override
    public void update(Meal meal) {
        super.update(meal);
    }
}