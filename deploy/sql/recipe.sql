CREATE USER rcp WITH PASSWORD 'rcp';
SET ROLE rcp;

drop table if exists Category;
drop table if exists Comments;
drop table if exists Ingredients;
drop table if exists Ratings;
drop table if exists Steps;
drop table if exists Recipe;
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
    id bigint not null,
    authorID bigint,
    date varchar(255),
    difficulty integer not null,
    name varchar(255),
    time integer not null,
    primary key (id)
);
create table Category (
    id bigint not null,
    category integer,
    Recipe_ID bigint,
    primary key (id)
);

create table Comments (
    id bigint not null,
    comment varchar(255),
    Recipe_ID bigint,
    primary key (id)
);

create table Ingredients (
    id bigint not null,
    IngredientID bigint not null,
    quantite integer not null,
    Recipe_ID bigint,
    primary key (id)
);

create table Ratings (
    id bigint not null,
    rate integer not null,
    Recipe_ID bigint,
    primary key (id)
);

create table Steps (
    id bigint not null,
    steps varchar(255),
    Recipe_id bigint,
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
    add constraint FK1f9l28viiu893aug0bi3d4ji6
    foreign key (Recipe_id)
    references Recipe;

INSERT INTO Recipe (id, authorID, date, difficulty, name, time) values (nextval('RECIPE_SEQ'), 1,'21/02/2020', 3, 'pizza', 1);
INSERT INTO Category(id, category, Recipe_ID) values (nextval('CATEGORY_SEQ'), 1, currval('RECIPE_SEQ');
INSERT INTO Comments(id, comment, Recipe_ID) values (nextval('COMMENTS_SEQ'),'Mauvais',currval('RECIPE_SEQ');
INSERT INTO Ingredients(id, IngredientID, quantite, Recipe_id) values (nextval('INGREDIENTS_SEQ'), 4, 2, currval('RECIPE_SEQ'));
INSERT INTO Ratings(id, rate, Recipe_ID) values (nextval('RATINGS_SEQ'), 4, currval('RECIPE_SEQ'));
INSERT INTO Steps (id, steps, Recipe_id) values (nextval('STEPS_SEQ'),'mettre dans le four',currval('RECIPE_SEQ'));
