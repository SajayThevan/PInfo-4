CREATE USER chlg WITH PASSWORD 'chlg';

drop table Challenge if exists;
drop sequence if exists CHALLENGE_SEQ;
create sequence CHALLENGE_SEQ start with 1 increment by 50;
create table Challenge (
        id bigint not null,
        authorID integer not null,
        name varchar(255),
        primary key (id)
    );
