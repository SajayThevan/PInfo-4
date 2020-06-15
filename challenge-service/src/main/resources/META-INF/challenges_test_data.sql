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
INSERT INTO Ingredient (ingredientId, quantity, id) values (4,20,nextval('INGREDIENT_SEQ'));
INSERT INTO Recipe (recipeId, id) values (14, nextval('RECIPE_SEQ'));

INSERT INTO Challenge (authorID, name, id) values ('14', 'CREPESAMERE', 2);
INSERT INTO Ingredient (ingredientId, quantity, id, CHALLENGE_ID) values (4,20,nextval('INGREDIENT_SEQ'),2);
INSERT INTO Recipe (recipeId, id, CHALLENGE_ID) values (14, nextval('RECIPE_SEQ'),2);

INSERT INTO Challenge (authorID, name, id) values ('1', 'GateauAuCaca', nextval('Challenge_SEQ'));
INSERT INTO Ingredient (ingredientId, quantity, id) values (4,10,nextval('INGREDIENT_SEQ'));
INSERT INTO Recipe (recipeId, id) values (14, nextval('RECIPE_SEQ'));

