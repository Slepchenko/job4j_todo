alter table todo_user add column role_id int not null references todo_roles (id);
