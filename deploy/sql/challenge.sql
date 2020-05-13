-- CREATE USER chlg WITH PASSWORD 'chlg';

drop table Recipe if exists;
drop table Ingredient if exists;
drop table Challenge if exists cascade;
drop sequence if exists INGREDIENT_SEQ;
drop sequence if exists RECIPE_SEQ;
drop sequence if exists CHALLENGE_SEQ;
create sequence INGREDIENT_SEQ start with 1 increment by 50;
create sequence CHALLENGE_SEQ start with 1 increment by 50;
create sequence RECIPE_SEQ start with 1 increment by 50;

create table Challenge (
    id bigint not null,
    authorID integer not null,
    name varchar(255),
    primary key (id)
);

create table Ingredient (
    id bigint not null,
    ingredientId bigint,
    quantity integer not null,
    CHALLENGE_ID bigint,
    primary key (id)
);

create table Recipe (
    id bigint not null,
    recipeId bigint,
    CHALLENGE_ID bigint,
    primary key (id)
);

alter table Ingredient
    add constraint FKaisyn8fyyxdnhm6knmgbt335i
    foreign key (CHALLENGE_ID)
    references Challenge;

alter table Recipe
    add constraint FKk503phg1qn2raeqfsvghkwymq
    foreign key (CHALLENGE_ID)
    references Challenge;

  GRANT SELECT, INSERT, UPDATE, DELETE, TRUNCATE ON ALL TABLES IN SCHEMA public to chlg;
  GRANT SELECT, UPDATE ON ALL SEQUENCES IN SCHEMA public to chlg;
  TRUNCATE TABLE INGREDIENT;

INSERT INTO Challenge (authorID, name, id) values (14, 'CREPESAMERE', Challenge_SEQ.nextval);
INSERT INTO Ingredient (ingredientId, quantity, id) values (4,20,INGREDIENT_SEQ.nextval);
INSERT INTO Recipe (recipeId, id) values (14, RECIPE_SEQ.nextval);
