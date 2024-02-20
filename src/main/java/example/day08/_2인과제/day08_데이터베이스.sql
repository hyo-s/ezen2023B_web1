drop database if exists day08;
create database day08;
use day08;

drop table if exists board;
create table board(
	bno int auto_increment,
    bcontent text,
    bwriter text,
    bpassword text,
    primary key( bno )
);

select * from board;