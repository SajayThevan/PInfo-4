drop table Recipe if exists;
drop sequence if exists RECIPIES_SEQ;
create sequence Recipe_SEQ start with 1 increment by 50;
create table Recipe (

        id bigint not null,
        authorID bigint not null,
        date varchar(255),
        difficulty integer not null,
        name varchar(255),
        time integer not null,
        primary key (recipeID)
       
);

INSERT INTO Recipe (id, authorID, date, difficulty, name, time) VALUES (RECIPES_SEQ.nextval, 1, 'demain',2,'Pizza',1);