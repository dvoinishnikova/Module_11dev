INSERT INTO client (name)
VALUES ('Sasha'),
       ('Anna'),
       ('Michael'),
       ('Sophia'),
       ('Olga'),
       ('Olivia'),
       ('James'),
       ('Ava'),
       ('Daniel'),
       ('Emma');


INSERT INTO planet (planet_id, name)
VALUES ('MARS', 'Mars'),
       ('ERTH', 'Earth'),
       ('SAT2', 'Saturn'),
       ('MERC','Mercury'),
       ('JUP7', 'Jupiter');

INSERT INTO ticket (client_id, from_planet_id, to_planet_id)
VALUES (1, 'ERTH', 'MERC'),
       (2, 'JUP7', 'SAT2'),
       (3, 'MARS', 'JUP7'),
       (4, 'MARS', 'SAT2'),
       (5, 'ERTH', 'JUP7'),
       (6, 'SAT2', 'ERTH'),
       (7, 'JUP7', 'ERTH'),
       (8, 'SAT2', 'MERC'),
       (9, 'MERC', 'MARS'),
       (10, 'MERC', 'JUP7');