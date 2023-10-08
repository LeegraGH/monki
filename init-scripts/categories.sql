CREATE TABLE IF NOT EXISTS categories
(
    id bigint NOT NULL,
    name character varying(255),
    CONSTRAINT categories_pkey PRIMARY KEY (id),
    CONSTRAINT ukt8o6pivur7nn124jehx7cygw5 UNIQUE (name)
);

insert into categories (id, name)
values  (2, 'Роллы'),
        (3, 'Рис'),
        (4, 'Лапша'),
        (5, 'Поке'),
        (1, 'Супы'),
        (6, 'Салаты'),
        (7, 'Напитки'),
        (8, 'Пицца'),
        (9, 'Гункан'),
        (252, 'Десерты');