CREATE USER prf WITH PASSWORD 'prf';

drop table Ingredient if exists;
drop table RecipeFav if exists;
drop table Profile if exists cascade;
drop sequence if exists INGREDIENT_SEQ;
drop sequence if exists RECIPEFAV_SEQ;
drop sequence if exists PROFILE_SEQ;
create sequence INGREDIENT_SEQ start with 1 increment by 50;
create sequence RECIPEFAV_SEQ start with 1 increment by 50;
create sequence PROFILE_SEQ start with 1 increment by 50;
create table Ingredient (
		id bigint not null,
        ingredientId bigint,
        quantity integer not null,
        Profile_id bigint,
        primary key (id)
    );
create table Profile (
        id bigint not null,
        email varchar(255),
        firstName varchar(255),
        lastName varchar(255),
        pseudo varchar(255),
        score integer not null,
        primary key (id)
    );

    create table RecipeFav (
        id bigint not null,
        recipeId bigint,
        Profile_id bigint,
        primary key (id)
    );

alter table Ingredient
        add constraint FK9ko8yb2rcb3tvgo925gwwpg0o
        foreign key (Profile_id)
        references Profile  ;

alter table RecipeFav
        add constraint FKhg5gulvbqrg71i0f99s389th5
        foreign key (Profile_id)
        references Profile;



INSERT INTO Profile (ID, email, firstName, lastName, pseudo, score) values ( PROFILE_SEQ.nextval, 'denizsungurtekin@gmail.com', 'deniz', 'gecer', 'malkah', 99);
INSERT INTO RecipeFav (recipeId, Profile_id, id) values (14,PROFILE_SEQ.currval,RECIPEFAV_SEQ.nextval);
INSERT INTO Ingredient (ingredientId, Profile_id, quantity, id) values (4, PROFILE_SEQ.currval,20,INGREDIENT_SEQ.nextval);

INSERT INTO Profile (ID, email, firstName, lastName, pseudo, score) values ( PROFILE_SEQ.nextval, 'sajay@gmail.com', 'sajay', 'trolong', 'ui', 0);
INSERT INTO RecipeFav (recipeId, Profile_id, id) values (25,PROFILE_SEQ.currval,RECIPEFAV_SEQ.nextval);
INSERT INTO Ingredient (ingredientId, Profile_id, quantity, id) values (5, PROFILE_SEQ.currval,22,INGREDIENT_SEQ.nextval);

INSERT INTO Profile (ID, email, firstName, lastName, pseudo, score) values ( PROFILE_SEQ.nextval, 'luke@gmail.com', 'luke', 'smith', 'inge', 0);
INSERT INTO RecipeFav (recipeId, Profile_id, id) values (7,PROFILE_SEQ.currval,RECIPEFAV_SEQ.nextval);
INSERT INTO Ingredient (ingredientId, Profile_id, quantity, id) values (2, PROFILE_SEQ.currval,30,INGREDIENT_SEQ.nextval);

INSERT INTO Profile (ID, email, firstName, lastName, pseudo, score) values ( PROFILE_SEQ.nextval, 'ella@gmail.com', 'ella', 'kummer', 'chef', 0);
INSERT INTO RecipeFav (recipeId, Profile_id, id) values (2,PROFILE_SEQ.currval,RECIPEFAV_SEQ.nextval);
INSERT INTO Ingredient (ingredientId, Profile_id, quantity, id) values (8, PROFILE_SEQ.currval,37,INGREDIENT_SEQ.nextval);

INSERT INTO Profile (ID, email, firstName, lastName, pseudo, score) values ( PROFILE_SEQ.nextval, 'mathias@gmail.com', 'mathias', 'tonini', 'dev', 0);
INSERT INTO RecipeFav (recipeId, Profile_id, id) values (4,PROFILE_SEQ.currval,RECIPEFAV_SEQ.nextval);
INSERT INTO Ingredient (ingredientId, Profile_id, quantity, id) values (12, PROFILE_SEQ.currval,26,INGREDIENT_SEQ.nextval);
