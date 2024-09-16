drop table category;

create table category (
	id serial8 primary key,
	name varchar
);

create table products (
    id serial8 primary key,
    name varchar,
    price varchar,
    size int2,
    category_id int8,
    foreign key (category_id) references category (id)
);


