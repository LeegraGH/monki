CREATE TABLE IF NOT EXISTS warehouse
(
    id bigint NOT NULL,
    count integer,
    product bigint,
    CONSTRAINT warehouse_pkey PRIMARY KEY (id),
    CONSTRAINT fk1lifrrwkhlewskkemqxjikjho FOREIGN KEY (product)
        REFERENCES public.products (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

insert into warehouse (id, count, product)
values  (2, 5, 755),
        (3, 3, 902),
        (4, 9, 952),
        (5, 14, 953),
        (6, 2, 954),
        (7, 3, 1002),
        (8, 5, 1003),
        (9, 7, 1004),
        (10, 2, 1005),
        (11, 1, 1006),
        (13, 8, 1008),
        (14, 3, 1009),
        (15, 9, 1052),
        (16, 11, 1102),
        (17, 16, 1104),
        (18, 3, 1302),
        (1, 1, 754),
        (12, 5, 1007);