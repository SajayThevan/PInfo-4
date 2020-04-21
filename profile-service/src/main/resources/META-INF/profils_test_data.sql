drop table Profile_favourite_recipes if exists;
drop table Profile_fridge_contents;
drop table Profile if exists;
drop table Profile_favourite_recipes if exists;
drop table Profile_fridge_contents if exists;
drop sequence if exists Profile_SEQ;
create sequence Profile_SEQ start with 1 increment by 50;
create table Profile (
        id bigint not null,
        email varchar(255),
        first_name varchar(255),
        last_name varchar(255),
        pseudo varchar(255),
        score integer not null,
        primary key (id)
    );
        
create table Profile_favourite_recipes (
        Profile_id bigint not null,
        favourite_recipes integer
    );

create table Profile_fridge_contents (
        Profile_id bigint not null,
        fridge_contents binary(255)
    );
 
alter table Profile_favourite_recipes 
        add constraint FKnx6lagkx01di86u4nuyi54skn 
        foreign key (Profile_id) 
        references Profile;
 
alter table Profile_fridge_contents 
        add constraint FKgn7r2u39aqyu73pgosr3s0pr8 
        foreign key (Profile_id) 
        references Profile;
    


INSERT INTO Profile (ID, email, first_name, last_name, pseudo, score) values ( Profile_SEQ.nextval, 'denizsungurtekin@gmail.com', 'deniz', 'gecer', 'malkah', 99);
INSERT INTO Profile_favourite_recipes (Profile_id, favourite_recipes) values (Profile_SEQ.currval, 10);
INSERT INTO Profile_fridge_contents (Profile_id, fridge_contents) values (Profile_SEQ.currval, 10);

INSERT INTO Profile (ID, email, first_name, last_name, pseudo, score) values ( Profile_SEQ.nextval, 'sajay@gmail.com', 'sajay', 'trolong', 'ui', 0);
INSERT INTO Profile_favourite_recipes (Profile_id, favourite_recipes) values (Profile_SEQ.currval, 11);
INSERT INTO Profile_fridge_contents (Profile_id, fridge_contents) values (Profile_SEQ.currval, 11);

INSERT INTO Profile (ID, email, first_name, last_name, pseudo, score) values ( Profile_SEQ.nextval, 'luke@gmail.com', 'luke', 'smith', 'inge', 0);
INSERT INTO Profile_favourite_recipes (Profile_id, favourite_recipes) values (Profile_SEQ.currval, 12);
INSERT INTO Profile_fridge_contents (Profile_id, fridge_contents) values (Profile_SEQ.currval, 12);

INSERT INTO Profile (ID, email, first_name, last_name, pseudo, score) values ( Profile_SEQ.nextval, 'ella@gmail.com', 'ella', 'kummer', 'chef', 0);
INSERT INTO Profile_favourite_recipes (Profile_id, favourite_recipes) values (Profile_SEQ.currval, 12);
INSERT INTO Profile_fridge_contents (Profile_id, fridge_contents) values (Profile_SEQ.currval, 12);

INSERT INTO Profile (ID, email, first_name, last_name, pseudo, score) values ( Profile_SEQ.nextval, 'mathias@gmail.com', 'mathias', 'tonini', 'dev', 0);
INSERT INTO Profile_favourite_recipes (Profile_id, favourite_recipes) values (Profile_SEQ.currval, 13);
INSERT INTO Profile_fridge_contents (Profile_id, fridge_contents) values (Profile_SEQ.currval, 13);

