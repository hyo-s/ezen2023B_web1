use springweb;

drop table if exists member;
create table member(
	no bigint auto_increment,					-- 회원 번호
    id varchar(15) not null unique,				-- 회원 아이디
    pw varchar(20) not null,					-- 회원 비밀번호
    name varchar(10) not null,					-- 회원 이름
    email varchar(20),							-- 회원 이메일
    phone varchar(13) not null unique,			-- 회원 핸드폰 번호
    img text,									-- 프로필 사진 경로
    constraint member_no_pk primary key(no)		-- 회원 번호 PK
);

select * from member;