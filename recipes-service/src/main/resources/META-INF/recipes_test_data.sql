 drop table Recipe_ratings if exists;
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