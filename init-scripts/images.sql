CREATE TABLE IF NOT EXISTS images
(
    id bigint NOT NULL,
    bytes oid,
    type character varying(255),
    file character varying(255),
    name character varying(255),
    size bigint,
    product bigint,
    CONSTRAINT images_pkey PRIMARY KEY (id),
    CONSTRAINT fka4kx7c93ssuvy4qkoptiy49hp FOREIGN KEY (product)
        REFERENCES products (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

insert into images (id, bytes, type, file, name, size, product)
values  (304, 24740, 'image/png', 'rolls1.png', 'file', 281040, 754),
        (305, 24741, 'image/png', 'soup1.png', 'file', 335094, 755),
        (452, 24749, 'image/png', 'pizza1.png', 'file', 425879, 902),
        (502, 24750, 'image/png', 'poke1.png', 'file', 375824, 952),
        (503, 24751, 'image/png', 'rice1.png', 'file', 403142, 953),
        (504, 24752, 'image/png', 'gunkan1.png', 'file', 230712, 954),
        (552, 24753, 'image/png', 'gunkan2.png', 'file', 197471, 1002),
        (553, 24754, 'image/png', 'gunkan3.png', 'file', 203816, 1003),
        (554, 24755, 'image/png', 'pizza2.png', 'file', 450544, 1004),
        (555, 24756, 'image/png', 'pizza3.png', 'file', 396605, 1005),
        (556, 24757, 'image/png', 'poke2.png', 'file', 388790, 1006),
        (557, 24758, 'image/png', 'rolls2.png', 'file', 249680, 1007),
        (558, 24759, 'image/png', 'rolls3.png', 'file', 259562, 1008),
        (559, 24760, 'image/png', 'pasta1.png', 'file', 325452, 1009),
        (602, 24761, 'image/png', 'soup2.png', 'file', 310277, 1052),
        (652, 24762, 'image/png', 'pasta2.png', 'file', 330466, 1102),
        (654, 24764, 'image/png', 'pasta3.png', 'file', 338061, 1104),
        (852, 24848, 'image/png', 'desert1.png', 'file', 349922, 1302);