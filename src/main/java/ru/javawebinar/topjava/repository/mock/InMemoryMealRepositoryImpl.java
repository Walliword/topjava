package ru.javawebinar.topjava.repository.mock;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, List<Meal>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(this::save);
    }

    @Override
    public Meal save(Meal meal) {

        Integer userId = meal.getUserId();
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            List<Meal> meals = repository.getOrDefault(userId, new ArrayList<Meal>() {{
                add(meal);
            }});
            //meals.add(meal);
            repository.put(userId, meals);
            return meal;
        }
        // treat case: update, but absent in storage
        List<Meal> meals = repository.get(userId);
        for (Meal m : meals) {
            if (m.getId().equals(meal.getId())) {
                meals.remove(m);
                break;
            }
        }
        meals.add(meal);
        repository.put(userId, meals);
        return meal;
    }

    @Override
    public void delete(int id, int userId) {
        List<Meal> meals = repository.get(userId);
        for (Meal m : meals) {
            if (m.getId() == id) {
                meals.remove(m);
                break;
            }
        }
    }

    @Override
    public Meal get(int id, int userId) {
        for (Meal m : repository.get(userId)) {
            if (m.getId().equals(id))
                return m;
        }
        return null;
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        return repository.get(userId).stream()
                .sorted((m1,m2) -> m2.getDateTime().compareTo(m1.getDateTime()))
                .collect(Collectors.toList());
    }
}

