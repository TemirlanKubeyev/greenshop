drop table if exists product, category, review;

create table category (
	id serial8 primary key,
	name varchar
);

create table product (
    id serial8 primary key,
    name varchar,
    price int,
    size int2,
    photo varchar,
    short_desc varchar,
    description text,
    category_id int8,
    foreign key (category_id) references category (id)
);

create table review (
    id serial8 primary key,
    score int2,
    review_text text,
    product_id int8,
    foreign key (product_id) references product (id)
);
