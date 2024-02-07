drop database if exists springweb;
create database springweb;
use springweb;

drop table if exists todo;
create table todo(
	id int auto_increment,			-- 할일 식별번호 / 자동번호
    content varchar(30),			-- 할일 내용
    deadline date,					-- 할일 마감일
    state boolean default false,	-- 할일 상태 [ true / false ]
    constraint todo_pk_id primary key(id)
);
select * from todo;

drop database if exists article;
create table article(
	id bigint auto_increment,
    title varchar(255),
    content varchar(255),
    constraint article_pk_id primary key (id)
);
select * from article;

insert into article(title,content) values('가나다','가나다');
