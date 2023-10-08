CREATE TABLE IF NOT EXISTS order_information
(
    order_id bigint NOT NULL,
    information character varying(255),
    CONSTRAINT fk4gm2qicql8665mgxhg2v55fot FOREIGN KEY (order_id)
        REFERENCES public.orders (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);