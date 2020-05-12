drop table Ingredient if exists;
drop sequence if exists INGREDIENT_SEQ;
create sequence INGREDIENT_SEQ start with 1 increment by 1;
create table Ingredient (
        id bigint not null,
		name varchar(255),
		kcal double not null,
		fat double not null,
		cholesterol double not null,
		protein double not null,
		salt double not null
);

INSERT INTO Ingredient (id, name, kcal, fat, cholesterol, protein, salt) values ( INGREDIENT_SEQ.nextval, 'chocolat', 2, 5, 3, 2, 1);
INSERT INTO Ingredient (id, name, kcal, fat, cholesterol, protein, salt) values ( INGREDIENT_SEQ.nextval, 'amande', 3, 2, 1.7, 3, 2);
INSERT INTO Ingredient (id, name, kcal, fat, cholesterol, protein, salt) values ( INGREDIENT_SEQ.nextval, 'noix', 3, 5, 4, 2, 3);
INSERT INTO Ingredient (id, name, kcal, fat, cholesterol, protein, salt) values ( INGREDIENT_SEQ.nextval, 'pate', 4.1, 2, 2, 4, 1);
INSERT INTO Ingredient (id, name, kcal, fat, cholesterol, protein, salt) values ( INGREDIENT_SEQ.nextval, 'sucre', 10, 5.3, 3, 2, 1);
INSERT INTO Ingredient (id, name, kcal, fat, cholesterol, protein, salt) values ( INGREDIENT_SEQ.nextval, 'sel', 3, 2, 1, 3, 2);
INSERT INTO Ingredient (id, name, kcal, fat, cholesterol, protein, salt) values ( INGREDIENT_SEQ.nextval, 'fajitas', 3, 5, 4, 2, 3);
INSERT INTO Ingredient (id, name, kcal, fat, cholesterol, protein, salt) values ( INGREDIENT_SEQ.nextval, 'tofu', 4, 2, 2, 4, 1);
INSERT INTO Ingredient (id, name, kcal, fat, cholesterol, protein, salt) values ( INGREDIENT_SEQ.nextval, 'salade', 2, 5, 3, 2, 1);
INSERT INTO Ingredient (id, name, kcal, fat, cholesterol, protein, salt) values ( INGREDIENT_SEQ.nextval, 'tomates', 3, 2, 1, 3, 2);
INSERT INTO Ingredient (id, name, kcal, fat, cholesterol, protein, salt) values ( INGREDIENT_SEQ.nextval, 'pain', 3, 5, 4, 2, 3);
INSERT INTO Ingredient (id, name, kcal, fat, cholesterol, protein, salt) values ( INGREDIENT_SEQ.nextval, 'chocolat noir', 4, 2, 2, 4, 1);
INSERT INTO Ingredient (id, name, kcal, fat, cholesterol, protein, salt) values ( INGREDIENT_SEQ.nextval, 'guacamole', 2, 5, 3, 2, 1);
