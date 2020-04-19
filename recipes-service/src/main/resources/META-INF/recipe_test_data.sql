drop table Recipes if exists;
drop sequence if exists RECIPIES_SEQ;
create sequence RECIPES_SEQ start with 1 increment by 50;
create table Recipes (

        recipeID bigint not null,
        name varchar(255) not null,
        author int not null,
        data timestamp,
        difficulty Int,
        time Int,
        primary key (recipeID)
);

INSERT INTO recipes(recipeID, name, author, data, difficulty, time) VALUES (RECIPES_SEQ.nextval,'Pâtes au thon', 1, PARSEDATETIME('09/22/2020','mm/dd/yyyy','en'), 3,2,5);