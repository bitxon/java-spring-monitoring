CREATE TABLE "order" (
	id SERIAL PRIMARY KEY,
    product_identifier VARCHAR ( 50 ) NOT NULL,
    product_name VARCHAR ( 100 ) NOT NULL,
    quantity INT NOT NULL,
	total_amount INT NOT NULL
);