CREATE TABLE users (
                       username VARCHAR(255) PRIMARY KEY,
                       password VARCHAR(255) NOT NULL,
                       role VARCHAR(255) NOT NULL
);

CREATE TABLE user_profiles (
                               id SERIAL PRIMARY KEY,
                               name VARCHAR(255),
                               city VARCHAR(255)
);

ALTER TABLE users
    ADD COLUMN user_profile_id INT,
    ADD CONSTRAINT fk_user_profile
        FOREIGN KEY (user_profile_id) REFERENCES user_profiles (id);
CREATE TABLE IF NOT EXISTS chats
(
    id serial NOT NULL,
    CONSTRAINT chats_pkey PRIMARY KEY (id)
    );
CREATE TABLE IF NOT EXISTS public.messages
(
    id serial NOT NULL,
    text character varying(255) ,
    "time" timestamp without time zone,
    chat_id bigint,
    user_name character varying(255) ,
    file_url character varying(255) ,
    CONSTRAINT messages_pkey PRIMARY KEY (id),
    CONSTRAINT fk64w44ngcpqp99ptcb9werdfmb FOREIGN KEY (chat_id)
    REFERENCES public.chats (id) MATCH SIMPLE
                     ON UPDATE NO ACTION
                     ON DELETE NO ACTION,
    CONSTRAINT fkc0tro07lxqf0m0nd9kynycupc FOREIGN KEY (user_name)
    REFERENCES public.users (username) MATCH SIMPLE
                     ON UPDATE NO ACTION
                     ON DELETE NO ACTION
    );
CREATE TABLE IF NOT EXISTS public.chat_users
(
    chat_id serial NOT NULL,
    user_username character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT chat_users_pkey PRIMARY KEY (chat_id, user_username),
    CONSTRAINT fk9eot9qfa4xa0qj2qb8k7l72ub FOREIGN KEY (user_username)
    REFERENCES public.users (username) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION,
    CONSTRAINT fklblrwneuhods5qvh9od8yyewy FOREIGN KEY (chat_id)
    REFERENCES public.chats (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    );
insert into user_profiles(name, city)
VALUES ('Иван', 'Самара'),
       ('Олег', 'Москва'),
       ('Максим', 'Пенза');

insert into users(username, password, role, user_profile_id)
VALUES ('user', 'user', 'USER', 1),
       ('admin', 'admin', 'ADMIN', 2),
       ('manager', 'manager', 'MANAGER', 3);