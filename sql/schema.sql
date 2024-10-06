drop table if exists product;
drop table if exists category;

create table category (
	id serial8 primary key,
	name varchar
);

create table product (
    id serial8 primary key,
    name varchar,
    price int,
    size int2,
    category_id int8,
    photo varchar,
    foreign key (category_id) references category (id)
);


