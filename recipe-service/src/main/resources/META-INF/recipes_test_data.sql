drop table Category if exists;
drop table Comments if exists;
drop table Ingredients if exists;
drop table Ratings if exists;
drop table Steps if exists;
drop table Recipe if exists cascade;
drop sequence if exists RECIPE_SEQ;
drop sequence if exists CATEGORY_SEQ;
drop sequence if exists COMMENTS_SEQ;
drop sequence if exists INGREDIENTS_SEQ;
drop sequence if exists RATINGS_SEQ;
drop sequence if exists STEPS_SEQ;
create sequence CATEGORY_SEQ start with 1 increment by 50;
create sequence COMMENTS_SEQ start with 1 increment by 50;
create sequence INGREDIENTS_SEQ start with 1 increment by 50;
create sequence RATINGS_SEQ start with 1 increment by 50;
create sequence STEPS_SEQ start with 1 increment by 50;
create sequence RECIPE_SEQ start with 1 increment by 1;
create table Recipe (
    id int8 not null,
    authorID varchar(255),
    date varchar(255),
    difficulty int4 not null,
    imagePath varchar(255),
    name varchar(255),
    time int4 not null,
    primary key (id)
);
create table Category (
    id int8 not null,
    category int4,
    Recipe_ID int8,
    primary key (id)
);

create table Comments (
    id int8 not null,
    comment varchar(255),
    Recipe_ID int8,
    primary key (id)
);

create table Ingredients (
    id int8 not null,
    IngredientID int8 not null,
    quantite int4 not null,
    Recipe_ID int8,
    primary key (id)
);

create table Ratings (
    id int8 not null,
    rate int4 not null,
    Recipe_ID int8,
    primary key (id)
);

create table Steps (
    id int8 not null,
    steps varchar(255),
    Recipe_ID int8,
    primary key (id)
);

alter table Category 
    add constraint FK3id5k3clok8i99x858e0au3oc 
    foreign key (Recipe_ID) 
    references Recipe;

alter table Comments 
    add constraint FKitod1h0fmt0dfg3399h3hl3vx 
    foreign key (Recipe_ID) 
    references Recipe;

alter table Ingredients 
    add constraint FKpx948xuk56dhbwrt0owopr8au 
    foreign key (Recipe_ID) 
    references Recipe;

alter table Ratings 
    add constraint FK8bdfwavk430j695y0v6f1nwc2 
    foreign key (Recipe_ID) 
    references Recipe;

alter table Steps 
    add constraint FKomt7cohr7gjkpxgwnvio7767m 
    foreign key (Recipe_ID) 
    references Recipe;
    
INSERT INTO Recipe (id, authorID, date, difficulty, imagePath, name, time) values (nextval('RECIPE_SEQ'), 'hjgfgkdhgkjf','21/02/2020', 3,'/tmp/images/recipe1', 'pizza', 1);
INSERT INTO Recipe (id, authorID, date, difficulty, imagePath, name, time) values (2, 'bfdkjshflkjsd','21/02/2020', 3, '/tmp/images/recipe2', 'pizza', 1);

INSERT INTO Category(id, category, Recipe_ID) values (nextval('CATEGORY_SEQ'),1, currval('RECIPE_SEQ'));


INSERT INTO Comments(id, comment, Recipe_ID) values (nextval('COMMENTS_SEQ'),'Mauvais',currval('RECIPE_SEQ'));
INSERT INTO Comments(id, comment, Recipe_ID) values (nextval('COMMENTS_SEQ'),'Bon',currval('RECIPE_SEQ'));
INSERT INTO Comments(id, comment, Recipe_ID) values (nextval('COMMENTS_SEQ'),'Mauvais',2);
INSERT INTO Comments(id, comment, Recipe_ID) values (nextval('COMMENTS_SEQ'),'Bon',2);

INSERT INTO Ingredients(id, IngredientID, quantite, Recipe_id) values (90, 4,2,currval('RECIPE_SEQ'));
INSERT INTO Ingredients(id, IngredientID, quantite, Recipe_id) values (91, 4,2,2);

INSERT INTO Ratings(id, rate, Recipe_ID) values (nextval('INGREDIENTS_SEQ'), 4, currval('RECIPE_SEQ'));
INSERT INTO Ratings(id, rate, Recipe_ID) values (nextval('INGREDIENTS_SEQ'), 4,2);

INSERT INTO Steps (id, steps, Recipe_id) values (nextval('STEPS_SEQ'),'mettre dans le four',currval('RECIPE_SEQ'));
INSERT INTO Steps (id, steps, Recipe_id) values (nextval('STEPS_SEQ'),'mettre dans le four',2);

