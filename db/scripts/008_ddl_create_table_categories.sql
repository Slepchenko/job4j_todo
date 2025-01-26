CREATE TABLE categories (
   id SERIAL PRIMARY KEY,
   name TEXT UNIQUE NOT NULL
);

insert into categories(name) values ('Категория_1');
insert into categories(name) values ('Категория_2');
insert into categories(name) values ('Категория_3');
insert into categories(name) values ('Категория_4');