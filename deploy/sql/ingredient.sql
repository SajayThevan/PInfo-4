CREATE USER ingd WITH PASSWORD 'ingd';

drop table Ingredient if exists;
drop sequence if exists INGREDIENT_SEQ;
create sequence INGREDIENT_SEQ start with 1 increment by 1;
create table Ingredient (
    id bigint not null,
		name varchar(255)
);
GRANT SELECT, INSERT, UPDATE, DELETE, TRUNCATE ON ALL TABLES IN SCHEMA public to ingd;
GRANT SELECT, UPDATE ON ALL SEQUENCES IN SCHEMA public to ingd;
TRUNCATE TABLE INGREDIENT;
