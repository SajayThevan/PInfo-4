drop table Ingredient if exists;
drop sequence if exists INGREDIENT_SEQ;
create sequence INGREDIENT_SEQ start with 1 increment by 1;
create table Ingredient (
    id  bigserial not null,
    cholesterol float8 not null,
    fat float8 not null,
    kcal float8 not null,
    name varchar(255),
    protein float8 not null,
    salt float8 not null,
    primary key (id)
);

INSERT INTO Ingredient (id, name, kcal, fat, cholesterol, protein, salt) values ( nextval('INGREDIENT_SEQ'), 'chocolat', 2, 5, 3, 2, 1);
INSERT INTO Ingredient (id, name, kcal, fat, cholesterol, protein, salt) values ( nextval('INGREDIENT_SEQ'), 'amande', 3, 2, 1.7, 3, 2);
INSERT INTO Ingredient (id, name, kcal, fat, cholesterol, protein, salt) values ( nextval('INGREDIENT_SEQ'), 'noix', 3, 5, 4, 2, 3);
INSERT INTO Ingredient (id, name, kcal, fat, cholesterol, protein, salt) values ( nextval('INGREDIENT_SEQ'), 'pate', 4.1, 2, 2, 4, 1);
INSERT INTO Ingredient (id, name, kcal, fat, cholesterol, protein, salt) values ( nextval('INGREDIENT_SEQ'), 'sucre', 10, 5.3, 3, 2, 1);
INSERT INTO Ingredient (id, name, kcal, fat, cholesterol, protein, salt) values ( nextval('INGREDIENT_SEQ'), 'sel', 3, 2, 1, 3, 2);
INSERT INTO Ingredient (id, name, kcal, fat, cholesterol, protein, salt) values ( nextval('INGREDIENT_SEQ'), 'fajitas', 3, 5, 4, 2, 3);
INSERT INTO Ingredient (id, name, kcal, fat, cholesterol, protein, salt) values ( nextval('INGREDIENT_SEQ'), 'tofu', 4, 2, 2, 4, 1);
INSERT INTO Ingredient (id, name, kcal, fat, cholesterol, protein, salt) values ( nextval('INGREDIENT_SEQ'), 'salade', 2, 5, 3, 2, 1);
INSERT INTO Ingredient (id, name, kcal, fat, cholesterol, protein, salt) values ( nextval('INGREDIENT_SEQ'), 'tomates', 3, 2, 1, 3, 2);
INSERT INTO Ingredient (id, name, kcal, fat, cholesterol, protein, salt) values ( nextval('INGREDIENT_SEQ'), 'pain', 3, 5, 4, 2, 3);
INSERT INTO Ingredient (id, name, kcal, fat, cholesterol, protein, salt) values ( nextval('INGREDIENT_SEQ'), 'chocolat noir', 4, 2, 2, 4, 1);
INSERT INTO Ingredient (id, name, kcal, fat, cholesterol, protein, salt) values ( nextval('INGREDIENT_SEQ'), 'guacamole', 2, 5, 3, 2, 1);
