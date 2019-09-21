

SET MODE PostgreSQL;


CREATE DATABASE animal_wild;

\c animal_wild;

CREATE TABLE animals (id SERIAL PRIMARY KEY, name varchar, type varchar);

CREATE TABLE sightings (id SERIAL PRIMARY KEY, location varchar,animal_id integer,ranger_name varchar,endangered_id integer);

CREATE TABLE endangered_animals(id serial PRIMARY KEY,name varchar,health varchar,age varchar);

CREATE DATABASE animal_wild_test WITH TEMPLATE animal_wild;
