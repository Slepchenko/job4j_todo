CREATE TABLE tasks (
   id SERIAL PRIMARY KEY,
   name character(60),
   description TEXT,
   created TIMESTAMP,
   done BOOLEAN
);