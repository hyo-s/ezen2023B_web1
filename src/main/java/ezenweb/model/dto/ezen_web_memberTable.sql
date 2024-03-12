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
select * from breply;

# ======================== 제품 DB ======================== #
# 제품 1개당 이미지 여러개 등록 : 1:M
# 제품 1개당 이미지 개수가 정해져 있음 [ 무조건 3개 ] 필드 3개
# 1. 제품
drop table if exists product;
create table product(
	pno int auto_increment,					# 제품번호
    pname varchar(100) not null,			# 제품이름
    pprice int default 0,					# 제품가격
    pcontent varchar(255),					# 제품설명
    pstate tinyint default 0,				# 제품상태
    pdate datetime default now(),			# 제품등록일
    plat varchar(30) not null,				# 제품위치 경도
    plng varchar(30) not null,				# 제품위치 위도
    mno bigint,								# 회원번호
    constraint product_pno_pk primary key(pno),
    constraint product_mno_fk foreign key(mno) references member(no) on update cascade on delete cascade
);
select * from product;

# 2. 제품 이미지
drop table if exists productimg;
create table productimg(
	pimgno int auto_increment,				# 제품이미지 식별번호
    pimg varchar(255),						# 제품이미지 파일명
    pno int,								# 제품번호
    constraint productimg_pimgno_pk primary key(pimgno),
    constraint productimg_pno_fk foreign key(pno) references product(pno) on update cascade on delete cascade
);
select * from productimg;


/*
	현재페이지-1 * 페이지당게시물 수
    
*/

# 1. 총(전체) 페이지 수 : 전체게시물 수 / 페이지 당 게시물 수
select count(*) from board b inner join member m on b.mno = m.no;

# 2. pageBoardSize = 5
# = 10 / 5 totalPage => 2 13/5 => totalPage => 2.6 + 1 ( 나머지가 있으면 +1 ) 

# ========================= 카테고리 / 검색 포함 ========================= //
# 총 레코드 / 게시물 수 : select count(*) from board;
# 제한된 개수만큼 게시물 출력 : select * from board b inner join member m on b.mno = m.no order by b.date desc limit ?, ?;

# 1. [ 조건추가 ] 카테고리 만큼의 레코드 수
select count(*) from board where bcno = 1; # 1. bcno=1 ( 자유 )만 레코드 수
select count(*) from board where bcno = 2; # 1. bcno=2 ( 노하우 )만 레코드 수
select count(*) from board where bcno = 0;

# 2. [ 조건추가 ] 카테고리 조건이 포함된 제한된 개수만큼 게시물 출력
	# 1. 자유 카테고리의 1페이지
select * from board b inner join member m on b.mno = m.no where bcno = 1 order by b.bdate desc limit 0,5;
	# 2. 노하우 카테고리의 1페이지
select * from board b inner join member m on b.mno = m.no where bcno = 1 order by b.bdate desc limit 0,5;
	# 3. 전체 카테고리의 1페이지
select * from board b inner join member m on b.mno = m.no order by b.bdate desc limit 0,5;

# 3. [ 검색 조건추가 ]
select count(*) from board where bcno = 1 and btitle like '%java%' ; # 제목에 'java'가 포함되어 있는 게시물 출력
select count(*) from board where bcno = 1 and bcontent like '%java%' ; # 내용에 'java'가 포함되어 있는 게시물 출력

select count(*) from board b inner join member m on b.mno = m.no where b.bcno = 1 and m.id like '%aaa%'; # 작성자 아이디로 검색 시 회원테이블과 조인 필요

# select count(*) from board b inner join member m on b.mno = m.no where b.bcno = 1 and key like '%keyword%';

update board set bview = bview+1 where bno = 1;
select * from board;

# =========================== 게시물 삭제 / 레코드 삭제 =========================== #
# 1. 삭제
# delete from board; 모든 레코드 삭제 : DML 복구 가능
# truncate board; 모든 레코드 삭제 : DDL 복구 불가능

# 2. 특정 레코드 삭제
delete from board where bno=5;

update board set btitle = 'a', bcontent = 'a', bcno = 2, bfile = 'aa.jpg' where bno = 1;
