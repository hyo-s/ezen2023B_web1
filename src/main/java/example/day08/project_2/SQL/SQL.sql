drop database if exists todoDB;
create database todoDB;
use todoDB;

create table todoTable(
	id int auto_increment,
    content varchar(30),
    tcondition boolean default false,
    tdate datetime default now(),

    primary key(id)
);

select * from todoTable;

insert into todoTable(content) values('1번 일');
insert into todoTable(content) values('2번 일');
insert into todoTable(content) values('3번 일');
insert into todoTable(content) values('4번 일');
insert into todoTable(content) values('5번 일');


select * from todoTable;
select * from todoTable order by id;


update todoTable set tcondition = true where id = 2;