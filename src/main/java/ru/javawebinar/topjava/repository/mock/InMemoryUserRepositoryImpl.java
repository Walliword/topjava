package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.util.UsersUtil;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryUserRepositoryImpl implements UserRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryUserRepositoryImpl.class);
    private Map<Integer, User> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        UsersUtil.USERS.forEach(this::save);
    }

    public static void main(String[] args) {
        InMemoryUserRepositoryImpl impl = new InMemoryUserRepositoryImpl();
        System.out.println(impl.repository);
        System.out.println();
        System.out.println(impl.repository.keySet());
        System.out.println(impl.repository.values());
        System.out.println(impl.delete(1));
        System.out.println(impl.delete(4));
        System.out.println(impl.repository.keySet());
        System.out.println(impl.repository.values());
        impl.save(new User(null, "Petr", "andrew@mail.ru", "12345", Role.ROLE_USER));
        System.out.println(impl.repository);
        System.out.println();
        System.out.println(impl.getByEmail("andrew@mail.ru"));
        System.out.println();
        System.out.println(impl.getAll());
    }

    @Override
    public boolean delete(int id) {
        log.info("delete {}", id);
        return repository.remove(id) != null;
    }

    @Override
    public User save(User user) {
        log.info("save {}", user);
        if (user.isNew()) {
            user.setId(counter.incrementAndGet());
            repository.put(user.getId(), user);
            return user;
        }
        return repository.computeIfAbsent(user.getId(), oldUser -> user);
    }

    @Override
    public User get(int id) {
        log.info("get {}", id);
        return repository.get(id);
    }

    @Override
    public List<User> getAll() {
        log.info("getAll");
        return repository.values().stream()
                .sorted(Comparator.comparing(User::getName).thenComparing(User::getId))
                .collect(Collectors.toList());
    }

    @Override
    public User getByEmail(String email) {
        log.info("getByEmail {}", email);

        return repository.values().stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst().orElse(null);
    }
}
