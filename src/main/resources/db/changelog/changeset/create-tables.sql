CREATE TABLE goods (
                       id_good SERIAL PRIMARY KEY,
                       id_type INTEGER NOT NULL,
                       Title VARCHAR(15) NOT NULL,
                       id_prod INTEGER NOT NULL,
                       CONSTRAINT fk_goods_types FOREIGN KEY (id_type) REFERENCES types(id_type),
                       CONSTRAINT fk_goods_producers FOREIGN KEY (id_prod) REFERENCES producers(id_prod)
);

CREATE TABLE goods_orders (
                              id_go SERIAL PRIMARY KEY,
                              id_goods INTEGER NOT NULL,
                              Order_Date DATE NOT NULL,
                              Arrive_Date DATE NOT NULL,
                              id_cust INTEGER NOT NULL,
                              Amount INTEGER NOT NULL,
                              Price NUMERIC(7,2) NOT NULL,
                              id_emp INTEGER NOT NULL,
                              CONSTRAINT fk_goods_orders_goods FOREIGN KEY (id_goods) REFERENCES goods(id_good),
                              CONSTRAINT fk_goods_orders_customers FOREIGN KEY (id_cust) REFERENCES customers(id_cust),
                              CONSTRAINT fk_goods_orders_employees FOREIGN KEY (id_emp) REFERENCES employees(id_emp)
);
