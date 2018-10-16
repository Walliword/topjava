package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;

import java.util.Collection;

public interface MealService {

    Meal create(int userId, Meal meal);

    void delete(int userId, int id);

    Meal get(int userId, int id);

    Meal update(int userId, Meal meal);

    Collection<Meal> getAll(int userId);
}