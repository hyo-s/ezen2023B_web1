drop database if exists springweb;
create database springweb;
use springweb;


drop table if exists member;
create table member(
	no bigint auto_increment,
    id varchar(30) not null unique,
    pw varchar(30) not null,
    name varchar(20) not null,
    email varchar(50),
    phone varchar(13) not null unique,
    img varchar(255) default 'default.jpg',
    mdate datetime default now() not null,
    constraint member_no_pk primary key(no)
);

select * from member;

# 1. 게시물 카테고리
drop table if exists bcategory;
create table bcategory(
	bcno int unsigned auto_increment,
    bcname varchar(30) not null unique,
    bcdate datetime default now() not null,
    constraint bcategory_bcno_pk primary key (bcno)
);
insert into bcategory( bcname ) values ('자유'),('노하우');
select * from bcategory;

# 2. 게시물
drop table if exists board;
create table board(
	bno bigint unsigned auto_increment,
    btitle varchar(255) not null,
    bcontent longtext,
    bfile varchar(255),
    bview int unsigned default 0 not null,
    bdate datetime default now() not null,
    mno bigint,
    bcno int unsigned,
    constraint board_bno_pk primary key(bno),
    constraint board_mno_fk foreign key(mno) references member(no) on update cascade on delete cascade,
    constraint board_bcno_fk foreign key(bcno) references bcategory(bcno) on update cascade on delete cascade
);
select * from board;

# 3. 게시물 댓글
drop table if exists breply;
create table breply(
	brno bigint unsigned auto_increment,
    brcontent varchar(255) not null,
    brdate datetime default now() not null,
    brindex bigint unsigned default 0 not null,
    mno bigint,
    bno bigint unsigned,
    constraint breply_brno_fk primary key(brno),
    constraint breply_mno_fk foreign key(mno) references member(no) on update cascade on delete cascade,
    constraint breply_bno_fk foreign key(bno) references board(bno) on update cascade on delete cascade
);

select * from breply;