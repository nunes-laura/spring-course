CREATE TABLE IF NOT EXISTS clients (
    id uuid NOT NULL,
    first_name character varying(255),
    last_name character varying(255),
    gender character varying(255),
    address character varying(255),
 PRIMARY KEY (id)
);