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
    authorID varchar(1024),
    date varchar(255),
    difficulty integer not null,
    imagePath varchar(255),
    name varchar(255),
    time integer not null,
    primary key (id)
);

create table Category (
    id bigint not null,
    categories integer,
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
    step varchar(255),
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
    add constraint FKomt7cohr7gjkpxgwnvio7767m
    foreign key (Recipe_id)
    references Recipe;
    

