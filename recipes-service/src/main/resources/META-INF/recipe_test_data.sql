drop table Recipes if exists;
drop sequence if exists RECIPIES_SEQ;
create sequence RECIPES_SEQ start with 1 increment by 50;
create table Recipes (
        -- instrumentType varchar(31) not null,
        -- id bigint not null,
        recipeID bigint not null,
        name varchar(255) not null,
        author varchar(255) not null,
        data timestamp,
        category varchar(255),
        difficulty Int,
        time Int,
        rating Int,
        -- TO ADD: INGREDIENTS, SETPS AND COMMENT
        primary key (recipeID)
);

--INSERT INTO Instrument (ID, instrumentType, brokerLei, counterpartyLei, dealDate, originalCurrency, valueDate, isin, tracker, quantity, maturityDate, amountInOriginalCurrency, strikeAmount, direction) values ( INSTRUMENT_SEQ.nextval, 'B', '335800DV2WKUAMUTYL21', '254900Z5WUXGPYH1WS92', PARSEDATETIME('10-07-2013','yyyy-dd-mm','en'), 'EUR', PARSEDATETIME('10-07-2013','yyyy-dd-mm','en'), 'BEC0000AIP48', NULL, 4969, PARSEDATETIME('09/22/2020','mm/dd/yyyy','en'), 539235.88, NULL, NULL  );
INSERT INTO recipes(recipeID, name, author, data, category, difficulty, time, rating) VALUES (RECIPES_SEQ.nextval,'Pâtes au thon', moi, PARSEDATETIME('09/22/2020','mm/dd/yyyy','en'), dinner, 3,2,5);