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

# 1. 게시물 전체 출력
select * from board;

# 2. 게시물 최신순으로 출력 ( order by )
select * from board order by bdate desc;

# 3. [작성자 아이디, 작성자 프로필]게시물 테이블과 회원 테이블 조인한 결과 출력 ( 테이블 합치기 ) select + select 
select * from board b inner join member m on b.mno = m.no order by b.bdate desc limit 0, 5;
# 특정 페이지
select * from board b inner join member m on b.mno = m.no where b.bno = 1;

# - 중복 필드 금지 ( PK - FK 제외 ) - 통계 필드 금지 ( 내역, 로그* )
# select 필드명 from 테이블명 inner join 테이블명 on 조인조건 where 일반조건 group by 그룹필드명 having 그룹조건 order by 정렬필드 desc/asc limit

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

/*
	현재페이지-1 * 페이지당게시물 수
    
*/

# 1. 총(전체) 페이지 수 : 전체게시물 수 / 페이지 당 게시물 수
select count(*) from board b inner join member m on b.mno = m.no;

# 2. pageBoardSize = 5
# = 10 / 5 totalPage => 2 13/5 => totalPage => 2.6 + 1 ( 나머지가 있으면 +1 ) 