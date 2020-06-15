CREATE USER chlg WITH PASSWORD 'chlg';
SET ROLE chlg;

drop table if exists Recipe;
drop table if exists Ingredient;
drop table if exists Challenge cascade;
drop sequence if exists INGREDIENT_SEQ;
drop sequence if exists RECIPE_SEQ;
drop sequence if exists CHALLENGE_SEQ;
create sequence INGREDIENT_SEQ start with 1 increment by 50;
create sequence CHALLENGE_SEQ start with 1 increment by 50;
create sequence RECIPE_SEQ start with 1 increment by 50;

create table Challenge (
    id int8 not null,
    authorID varchar(255),
    name varchar(255),
    primary key (id)
);
    
create table Ingredient (
    id int8 not null,
    ingredientId int8,
    quantity int4 not null,
    CHALLENGE_ID int8,
    primary key (id)
);
    
create table Recipe (
    id int8 not null,
    recipeId int8,
    CHALLENGE_ID int8,
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

INSERT INTO Challenge (authorID, name, id) values ('14', 'CREPESAMERE', nextval('Challenge_SEQ'));
INSERT INTO Ingredient (ingredientId, quantity, id, CHALLENGE_ID) values (4,20,nextval('INGREDIENT_SEQ'), currval('CHALLENGE_SEQ'));
INSERT INTO Recipe (recipeId, id, CHALLENGE_ID) values (14, nextval('RECIPE_SEQ'), currval('CHALLENGE_SEQ'));

INSERT INTO Challenge (authorID, name, id) values ('1', 'PANCAKESSAMERE', nextval('Challenge_SEQ'));
INSERT INTO Ingredient (ingredientId, quantity, id, CHALLENGE_ID) values (1,2,nextval('INGREDIENT_SEQ'), currval('CHALLENGE_SEQ'));
INSERT INTO Recipe (recipeId, id, CHALLENGE_ID) values (15, nextval('RECIPE_SEQ'), currval('CHALLENGE_SEQ'));
