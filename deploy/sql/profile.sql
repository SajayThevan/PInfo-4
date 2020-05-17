CREATE USER prf WITH PASSWORD 'prf';
CREATE ROLE common_role;
GRANT common_role TO prf, postgres;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON TABLES TO common_role;
SET ROLE prf;

drop table if exists Profile cascade;
create table Ingredient (
    id bigint not null,
    ingredientId bigint,
    quantity integer not null,
    Profile_id bigint,
    primary key (id)
);

INSERT INTO Profile (ID, email, firstName, lastName, pseudo, score) values ( 1, 'denizsungurtekin@gmail.com', 'deniz', 'gecer', 'malkah', 99);
