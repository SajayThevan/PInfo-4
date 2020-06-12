CREATE USER prf WITH PASSWORD 'prf';
SET ROLE prf;

drop table if exists Ingredient;
drop table if exists RecipeFav;
drop table if exists Profile cascade;
drop sequence if exists INGREDIENT_SEQ;
drop sequence if exists RECIPEFAV_SEQ;
create sequence INGREDIENT_SEQ start with 1 increment by 50;
create sequence RECIPEFAV_SEQ start with 1 increment by 50;
create table Ingredient (
    id bigint not null,
    ingredientId bigint,
    quantity integer not null,
    Profile_id varchar(255),
    primary key (id)
);
create table Profile (
    id varchar(255) not null,
    email varchar(255),
    firstName varchar(255),
    lastName varchar(255),
    pseudo varchar(255),
    score integer not null,
    image varchar(255),
    primary key (id)
);

create table RecipeFav (
    id bigint not null,
    recipeId bigint,
    Profile_id varchar(255),
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

INSERT INTO Profile    (ID, email, firstName, lastName, pseudo, score, image) values ('2', 'denizsungurtekin@gmail.com', 'deniz', 'gecer', 'malkah', 99,'/tmp/image/logo.png');
INSERT INTO RecipeFav  (recipeId, id, Profile_id)                      		  values (14, nextval('RECIPEFAV_SEQ'), '2');
INSERT INTO Ingredient (ingredientId, quantity, id, Profile_id)               values (4, 50, nextval('INGREDIENT_SEQ'), '2');
