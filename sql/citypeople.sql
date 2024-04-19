
create table city (
    id serial8 not null,
    cityname varchar (30) not null,
    primary key (id)


);

create table people (
    id serial8 not null,
    city_id int8 not null,
    name varchar(20) not null,
    primary key(id),
    foreign key(city_id) references city (id)
);

insert into city(cityname)
values ('Amsterdam'),
       ('NewYork'),
       ('Rio de Janeiro');

insert into people(city_id, name)
values (1, 'Joshua'),
       (1, 'Enrique'),
       (2, 'Helen'),
       (2, 'Kai'),
       (3, 'Samanta'),
       (3, 'Samuel'),
       (3, 'Paulu');

-- SELECT * FROM pg_tables WHERE tablename = 'people';
-- drop table city;


-- select c.id,
--        c.cityname,
--        c2.name
-- from city c
-- join citizens c2 on c.id = c2.city_id