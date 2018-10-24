DELETE FROM meals;
DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals (user_id, date_time, description, calories) VALUES
                                                                     (100000, '2018-10-23 09:00:00', 'Завтрак', 500),
                                                                     (100000, '2018-10-23 14:00:00', 'Обед', 500),
                                                                     (100000, '2018-10-23 20:00:00', 'Ужин', 1000),
                                                                     (100000, '2018-10-24 09:00:00', 'Завтрак', 500),
                                                                     (100000, '2018-10-24 14:00:00', 'Обед', 501),
                                                                     (100000, '2018-10-24 20:00:00', 'Ужин', 1000),
                                                                     (100001, '2018-10-23 12:00:00', 'еда админская', 1500),
                                                                     (100001, '2018-10-24 12:00:00', 'еда админская', 1500)
