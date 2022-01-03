
CREATE SCHEMA product AUTHORIZATION vasili;

CREATE TABLE product.card (
	id serial4 NOT NULL,
	discount int4 NULL,
	CONSTRAINT card_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX card_id_uindex ON product.card USING btree (id);

ALTER TABLE product.card ADD id serial4 NOT NULL;

ALTER TABLE product.card ADD discount int4 NULL;

CREATE TABLE product.product (
	"name" varchar NULL,
	id serial4 NOT NULL,
	price numeric NULL,
	count int4 NULL,
	CONSTRAINT product_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX product_id_uindex ON product.product USING btree (id);

INSERT INTO product.product ("name", id, price, count) VALUES('product1', 1, 1000, 100);
INSERT INTO product.product ("name", id, price, count) VALUES('product2', 2, 200, 5);
INSERT INTO product.product ("name", id, price, count) VALUES('product3', 3, 300, 36);
INSERT INTO product.product ("name", id, price, count) VALUES('product4', 4, 600, 45);
INSERT INTO product.product ("name", id, price, count) VALUES('product5', 5, 980, 18);

INSERT INTO product.card (id, discount) VALUES(1, 10);
INSERT INTO product.card (id, discount) VALUES(2, 20);
INSERT INTO product.card (id, discount) VALUES(3, 25);
INSERT INTO product.card (id, discount) VALUES(4, 54);
INSERT INTO product.card (id, discount) VALUES(5, 60);
INSERT INTO product.card (id, discount) VALUES(6, 5);

