 drop table Recipe_ratings if exists;
 drop table Recipe_steps if exists;
 drop table Recipe_ingredients if exists;
 drop table Recipe_comments if exists;
 drop table Recipe_category if exists;
 drop table Recipe if exists;
 drop sequence if exists Recipe_SEQ;
create sequence Recipe_SEQ start with 1 increment by 50;
create table Recipe (
    id bigint not null,
    authorID bigint,
    date varchar(255),
    difficulty integer not null,
    name varchar(255),
    time integer not null,
    primary key (id)
);
 
create table Recipe_category (
    Recipe_id bigint not null,
    category integer
);
 
create table Recipe_comments (
    Recipe_id bigint not null,
    comments varchar(255)
);
 
create table Recipe_ingredients (
    Recipe_id bigint not null,
    ingredients bigint
);
 
create table Recipe_ratings (
    Recipe_id bigint not null,
    ratings integer
);
 
create table Recipe_steps (
    Recipe_id bigint not null,
    steps varchar(255)
);
 
alter table Recipe_category 
    add constraint FKdnydw8utjtkqk14ebsa79pj7j 
    foreign key (Recipe_id) 
    references Recipe
 ;
alter table Recipe_comments 
    add constraint FKjml832u7gkr5g8akg7skbknyf 
    foreign key (Recipe_id) 
    references Recipe
 ;
alter table Recipe_ingredients 
    add constraint FKh3d27lqt0nm1o8klq2i5e819o 
    foreign key (Recipe_id) 
    references Recipe
 ;
alter table Recipe_ratings 
    add constraint FK6dxtx87fqo776ltqcyxfrmydl 
    foreign key (Recipe_id) 
    references Recipe
 ;
alter table Recipe_steps 
    add constraint FKk43rfyf9m3wu4ufawome7imst 
    foreign key (Recipe_id) 
    references Recipe
    
;



INSERT INTO Recipe (id, authorID, date, difficulty, name, time) values (Recipe_SEQ.nextval, 10,'21/02/2020', 3, 'pizza', 1);
INSERT INTO Recipe_category(Recipe_id, category) values (Recipe_SEQ.currval, 1);
INSERT INTO Recipe_comments(Recipe_id, comments) values (Recipe_SEQ.currval,'c était bon');
INSERT INTO Recipe_ingredients(Recipe_id, ingredients) values (Recipe_SEQ.currval, 2);
INSERT INTO Recipe_ratings(Recipe_id, ratings) values (Recipe_SEQ.currval, 5);
INSERT INTO Recipe_steps(Recipe_id, steps) values (Recipe_SEQ.currval,'ajouter les oeufs');


INSERT INTO Recipe (id, authorID, date, difficulty, name, time) values (Recipe_SEQ.nextval, 10,'22/02/2020', 1, 'Vodka',3);
INSERT INTO Recipe_category(Recipe_id, category) values (Recipe_SEQ.currval, 3);
INSERT INTO Recipe_comments(Recipe_id, comments) values (Recipe_SEQ.currval,'c était bon');
INSERT INTO Recipe_ingredients(Recipe_id, ingredients) values (Recipe_SEQ.currval, 2);
INSERT INTO Recipe_ratings(Recipe_id, ratings) values (Recipe_SEQ.currval, 7);
INSERT INTO Recipe_steps(Recipe_id, steps) values (Recipe_SEQ.currval,'Mets la vodka');

INSERT INTO Recipe (id, authorID, date, difficulty, name, time) values (Recipe_SEQ.nextval, 10,'12/02/2020', 1, 'Pain',3);
INSERT INTO Recipe_category(Recipe_id, category) values (Recipe_SEQ.currval, 3);
INSERT INTO Recipe_comments(Recipe_id, comments) values (Recipe_SEQ.currval,'c était Mauvais');
INSERT INTO Recipe_ingredients(Recipe_id, ingredients) values (Recipe_SEQ.currval, 2);
INSERT INTO Recipe_ratings(Recipe_id, ratings) values (Recipe_SEQ.currval, 3);
INSERT INTO Recipe_steps(Recipe_id, steps) values (Recipe_SEQ.currval,'melange la farine');

INSERT INTO Recipe (id, authorID, date, difficulty, name, time) values (Recipe_SEQ.nextval, 10,'2/02/2020', 1, 'fromage',3);
INSERT INTO Recipe_category(Recipe_id, category) values (Recipe_SEQ.currval, 3);
INSERT INTO Recipe_comments(Recipe_id, comments) values (Recipe_SEQ.currval,'c était moyen');
INSERT INTO Recipe_ingredients(Recipe_id, ingredients) values (Recipe_SEQ.currval, 2);
INSERT INTO Recipe_ratings(Recipe_id, ratings) values (Recipe_SEQ.currval, 3);
INSERT INTO Recipe_steps(Recipe_id, steps) values (Recipe_SEQ.currval,'lait');
