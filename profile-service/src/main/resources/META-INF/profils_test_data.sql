
drop table if exists Ingredient;
drop table if exists RecipeFav;
drop table if exists Profile cascade;
drop sequence if exists INGREDIENT_SEQ;
drop sequence if exists RECIPEFAV_SEQ;
create sequence INGREDIENT_SEQ start with 1 increment by 50;
create sequence RECIPEFAV_SEQ start with 1 increment by 50;
create table Ingredient (
	id int8 not null,
	ingredientId int8,
	quantity int4 not null,
	PROFILE_ID varchar(255),
	primary key (id)
    );
create table Profile (
    id varchar(255) not null,
    email varchar(255),
    firstName varchar(255),
    image varchar(255),
    lastName varchar(255),
    pseudo varchar(255),
    score int4 not null,
    primary key (id)
    );

create table RecipeFav (
    id int8 not null,
    recipeId int8,
    PROFILE_ID varchar(255),
    primary key (id)
);

alter table Ingredient 
    add constraint FK7ws72lu9rqdx04v8qxic0qiig 
    foreign key (PROFILE_ID) 
    references Profile;

alter table RecipeFav 
    add constraint FKhdk6t0ltv7acuih3cbpro17qk 
    foreign key (PROFILE_ID) 
    references Profile;

INSERT INTO Profile (ID, email, firstName, lastName, pseudo, score) values ( '30181a13-2157-4cb4-8298-e71dbfda4f01', 'mathias@gmail.com', 'Mathias', 'Tonini', 'mathi', 99);
INSERT INTO RecipeFav (recipeId, id, Profile_id) values (15, nextval('RECIPEFAV_SEQ'),'30181a13-2157-4cb4-8298-e71dbfda4f01');
INSERT INTO Ingredient (ingredientId, quantity, id, Profile_id) values (5, 50, nextval('INGREDIENT_SEQ'),'30181a13-2157-4cb4-8298-e71dbfda4f01');

INSERT INTO Profile    (ID, email, firstName, lastName, pseudo, score, image) values ('2', 'denizsungurtekin@gmail.com', 'deniz', 'gecer', 'malkah', 99,'/tmp/image/logo.png');
INSERT INTO RecipeFav  (recipeId, id, Profile_id)                      		  values (14, nextval('RECIPEFAV_SEQ'), '2');
INSERT INTO Ingredient (ingredientId, quantity, id, Profile_id)               values (4, 50, nextval('INGREDIENT_SEQ'), '2');




