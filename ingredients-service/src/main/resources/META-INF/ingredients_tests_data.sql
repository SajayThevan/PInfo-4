drop table Ingredients if exists;
drop sequence if exists INGREDIENT_SEQ;
create sequence INGREDIENT_SEQ start with 1 increment by 50;
create table Ingredients (
        id bigint not null,
		name varchar(255),
		kcal int not null,
		fat int not null,
		cholesterol int not null,
		protein int not null,
		salt int not null,
);

INSERT INTO Ingredients (id, name, kcal, cholesterol, protein, salt) values ( 1, 'poivron', 10, 0.5, 2, 1);
INSERT INTO Ingredients (id, name, kcal, cholesterol, protein, salt) values ( 2, 'aubergine', 11, 1, 3, 2);
INSERT INTO Ingredients (id, name, kcal, cholesterol, protein, salt) values ( 3, 'choco', 12, 1.5, 4,3);
INSERT INTO Ingredients (id, name, kcal, cholesterol, protein, salt) values ( INGREDIENT_SEQ.nextval, 'lait', 10, 0.5, 2, 1);
