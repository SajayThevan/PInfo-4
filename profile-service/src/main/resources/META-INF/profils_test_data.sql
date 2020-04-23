drop table Ingrediant if exists;
drop table RecetteFav if exists;
drop table Profile if exists;
drop sequence if exists INGREDIANT_SEQ;
drop sequence if exists RECETTEFAV_SEQ;
drop sequence if exists PROFILE_SEQ;
create sequence INGREDIANT_SEQ start with 1 increment by 50;
create sequence RECETTEFAV_SEQ start with 1 increment by 50;
create sequence PROFILE_SEQ start with 1 increment by 50;
create table Ingrediant (
		id bigint not null,
        Ingrediantid bigint,
        quantite integer not null,
        Profile_id bigint,
        primary key (id)
    );
create table Profile (
        id bigint not null,
        email varchar(255),
        first_name varchar(255),
        last_name varchar(255),
        pseudo varchar(255),
        score integer not null,
        primary key (id)
    );
        
    create table RecetteFav (
        id bigint not null,
        Recetteid bigint,
        Profile_id bigint,
        primary key (id)
    );
    
alter table Ingrediant 
        add constraint FKrdpn2snneh3pypmmm3ebh3i1b 
        foreign key (Profile_id) 
        references Profile  ;  
 
alter table RecetteFav 
        add constraint FKl63dy37w8aupfgv4oj2i5kf48 
        foreign key (Profile_id) 
        references Profile;
 
   

INSERT INTO Profile (ID, email, first_name, last_name, pseudo, score) values ( PROFILE_SEQ.nextval, 'denizsungurtekin@gmail.com', 'deniz', 'gecer', 'malkah', 99);
INSERT INTO RecetteFav (Recetteid, Profile_id, id) values (14,PROFILE_SEQ.currval,RECETTEFAV_SEQ.nextval);
INSERT INTO Ingrediant (Ingrediantid, Profile_id, quantite, id) values (4, PROFILE_SEQ.currval,20,INGREDIANT_SEQ.nextval);

INSERT INTO Profile (ID, email, first_name, last_name, pseudo, score) values ( PROFILE_SEQ.nextval, 'sajay@gmail.com', 'sajay', 'trolong', 'ui', 0);
INSERT INTO RecetteFav (Recetteid, Profile_id, id) values (25,PROFILE_SEQ.currval,RECETTEFAV_SEQ.nextval);
INSERT INTO Ingrediant (Ingrediantid, Profile_id, quantite, id) values (5, PROFILE_SEQ.currval,22,INGREDIANT_SEQ.nextval);

INSERT INTO Profile (ID, email, first_name, last_name, pseudo, score) values ( PROFILE_SEQ.nextval, 'luke@gmail.com', 'luke', 'smith', 'inge', 0);
INSERT INTO RecetteFav (Recetteid, Profile_id, id) values (7,PROFILE_SEQ.currval,RECETTEFAV_SEQ.nextval);
INSERT INTO Ingrediant (Ingrediantid, Profile_id, quantite, id) values (2, PROFILE_SEQ.currval,30,INGREDIANT_SEQ.nextval);

INSERT INTO Profile (ID, email, first_name, last_name, pseudo, score) values ( PROFILE_SEQ.nextval, 'ella@gmail.com', 'ella', 'kummer', 'chef', 0);
INSERT INTO RecetteFav (Recetteid, Profile_id, id) values (2,PROFILE_SEQ.currval,RECETTEFAV_SEQ.nextval);
INSERT INTO Ingrediant (Ingrediantid, Profile_id, quantite, id) values (8, PROFILE_SEQ.currval,37,INGREDIANT_SEQ.nextval);

INSERT INTO Profile (ID, email, first_name, last_name, pseudo, score) values ( PROFILE_SEQ.nextval, 'mathias@gmail.com', 'mathias', 'tonini', 'dev', 0);
INSERT INTO RecetteFav (Recetteid, Profile_id, id) values (4,PROFILE_SEQ.currval,RECETTEFAV_SEQ.nextval);
INSERT INTO Ingrediant (Ingrediantid, Profile_id, quantite, id) values (12, PROFILE_SEQ.currval,26,INGREDIANT_SEQ.nextval);

