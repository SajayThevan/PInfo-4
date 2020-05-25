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
    id bigint not null,
    authorID varchar(1024),
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
    
INSERT INTO Recipe (id, authorID, date, difficulty, name, time) values (RECIPE_SEQ.nextval, 'hjgfgkdhgkjf','21/02/2020', 3, 'pizza', 1);
INSERT INTO Recipe (id, authorID, date, difficulty, name, time) values (2, 'bfdkjshflkjsd','21/02/2020', 3, 'pizza', 1);

INSERT INTO Category(id, category, Recipe_ID) values (CATEGORY_SEQ.nextval,1, RECIPE_SEQ.currval);


INSERT INTO Comments(id, comment, Recipe_ID) values (COMMENTS_SEQ.nextval,'Mauvais',RECIPE_SEQ.currval);
INSERT INTO Comments(id, comment, Recipe_ID) values (COMMENTS_SEQ.nextval,'Bon',RECIPE_SEQ.currval);
INSERT INTO Comments(id, comment, Recipe_ID) values (COMMENTS_SEQ.nextval,'Mauvais',2);
INSERT INTO Comments(id, comment, Recipe_ID) values (COMMENTS_SEQ.nextval,'Bon',2);

INSERT INTO Ingredients(id, IngredientID, quantite, Recipe_id) values (90, 4,2,RECIPE_SEQ.currval);
INSERT INTO Ingredients(id, IngredientID, quantite, Recipe_id) values (91, 4,2,2);

INSERT INTO Ratings(id, rate, Recipe_ID) values (INGREDIENTS_SEQ.nextval, 4, RECIPE_SEQ.currval);
INSERT INTO Ratings(id, rate, Recipe_ID) values (INGREDIENTS_SEQ.nextval, 4,2);

INSERT INTO Steps (id, steps, Recipe_id) values (STEPS_SEQ.nextval,'mettre dans le four',RECIPE_SEQ.currval);
INSERT INTO Steps (id, steps, Recipe_id) values (STEPS_SEQ.nextval,'mettre dans le four',2);

