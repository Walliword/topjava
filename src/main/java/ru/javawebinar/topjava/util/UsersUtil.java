package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.Arrays;
import java.util.List;

public class UsersUtil {
    public static final List<User> USERS = Arrays.asList(
          new User(null, "Ivan", "ivan2016@mail.ru", "querty", Role.ROLE_ADMIN),
            new User(null, "Petr", "petr1990@mail.ru", "12345", Role.ROLE_USER),
            new User(null, "Dima", "dimakorol@mail.ru", "19122003", Role.ROLE_USER)
    );
}
