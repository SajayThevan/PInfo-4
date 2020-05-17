CREATE USER prf WITH PASSWORD 'prf';
CREATE ROLE common_role;
GRANT common_role TO prf;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON TABLES TO common_role;

drop table if exists Ingredient;
drop table if exists RecipeFav;
drop table if exists Profile cascade;
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

ALTER TABLE Ingredient OWNER TO prf;
ALTER TABLE Profile OWNER TO prf;
ALTER TABLE RecipeFav OWNER TO prf;

-- GRANT SELECT, INSERT, UPDATE, DELETE, ALTER, TRUNCATE ON ALL TABLES IN SCHEMA public to prf;
-- GRANT SELECT, UPDATE ON ALL SEQUENCES IN SCHEMA public to prf;
-- TRUNCATE TABLE PROFILE;

alter table Ingredient
        add constraint FK9ko8yb2rcb3tvgo925gwwpg0o
        foreign key (Profile_id)
        references Profile  ;

alter table RecipeFav
        add constraint FKhg5gulvbqrg71i0f99s389th5
        foreign key (Profile_id)
        references Profile;



INSERT INTO Profile (ID, email, firstName, lastName, pseudo, score) values ( PROFILE_SEQ.nextval, 'denizsungurtekin@gmail.com', 'deniz', 'gecer', 'malkah', 99);
INSERT INTO RecipeFav (recipeId, id) values (14, RECIPEFAV_SEQ.nextval);
INSERT INTO Ingredient (ingredientId, quantity, id) values (4, 50, INGREDIENT_SEQ.nextval);
