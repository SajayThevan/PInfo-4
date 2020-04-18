drop table Ingredient if exists;
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
INSERT INTO Instrument (id, name, kcal, cholesterol, protein, salt) values ( INGREDIENT_SEQ.nextval, 'poivron', 10, 0.5, 2, 1);
INSERT INTO Instrument (id, name, kcal, cholesterol, protein, salt) values ( INGREDIENT_SEQ.nextval, 'aubergine', 11, 1, 3, 2);
INSERT INTO Instrument (id, name, kcal, cholesterol, protein, salt) values ( INGREDIENT_SEQ.nextval, 'courgette', 12, 1.5, 4,3);