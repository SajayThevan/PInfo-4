CREATE USER chlg WITH PASSWORD 'chlg';
SET ROLE chlg;

drop table if exists Recipe;
drop table if exists Ingredients;
drop table if exists Challenge cascade;
drop sequence if exists INGREDIENTS_SEQ;
drop sequence if exists RECIPE_SEQ;
drop sequence if exists CHALLENGE_SEQ;
create sequence INGREDIENTS_SEQ start with 1 increment by 50;
create sequence CHALLENGE_SEQ start with 1 increment by 50;
create sequence RECIPE_SEQ start with 1 increment by 50;

create table Challenge (
    id bigint not null,
    authorID varchar(255),
    name varchar(255),
    primary key (id)
);

create table Ingredients (
    id bigint not null,
    ingredientsId bigint,
    CHALLENGE_ID bigint,
    primary key (id)
);

create table Recipe (
    id bigint not null,
    recipeId bigint,
    CHALLENGE_ID bigint,
    primary key (id)
);

alter table Ingredients
    add constraint FKaisyn8fyyxdnhm6knmgbt335i
    foreign key (CHALLENGE_ID)
    references Challenge;

alter table Recipe
    add constraint FKk503phg1qn2raeqfsvghkwymq
    foreign key (CHALLENGE_ID)
    references Challenge;
    
