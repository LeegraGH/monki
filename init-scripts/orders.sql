CREATE TABLE IF NOT EXISTS orders
(
    id bigint NOT NULL,
    address text,
    amount integer,
    comment text,
    name character varying(255),
    payment character varying(255),
    phone character varying(255),
    CONSTRAINT orders_pkey PRIMARY KEY (id)
);