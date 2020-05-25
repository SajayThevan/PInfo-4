drop table Ingredient if exists;
drop table RecipeFav if exists;
drop table Profile if exists cascade;
drop sequence if exists INGREDIENT_SEQ;
drop sequence if exists RECIPEFAV_SEQ;
drop sequence if exists PROFILE_SEQ;
create sequence INGREDIENT_SEQ start with 1 increment by 50;
create sequence RECIPEFAV_SEQ start with 1 increment by 50;

create table Ingredient (
		id bigint not null,
        ingredientId bigint,
        quantity integer not null,
        Profile_id bigint,
        primary key (id)
    );
create table Profile (
        id varchar(255) not null,
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
 



INSERT INTO Profile (ID, email, firstName, lastName, pseudo, score) values ( '2', 'denizsungurtekin@gmail.com', 'deniz', 'gecer', 'malkah', 99);
INSERT INTO RecipeFav (recipeId, id, Profile_id) values (14, RECIPEFAV_SEQ.nextval,'2');
INSERT INTO Ingredient (ingredientId, quantity, id, Profile_id) values (4, 50, INGREDIENT_SEQ.nextval,'2');



