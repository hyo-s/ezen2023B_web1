drop database if exists upteam4;
create database upteam4;
use upteam4;

# ================== 과목 ================== #
drop table if exists class;
create table class(
	classno int auto_increment primary key,			 # 과목 번호
    classname varchar(20) not null unique,			 # 과목 명
    classtype char(4) not null						 # 전공, 교양 여부
);
select * from class;
select classname from class;
insert into class(classname, classtype) values('수묵화의 심화','교양선택');
update class set classname = '수묵화의 심화 중 기초', classtype = '교양필수' where classno = 1;

# ================== 회원 ================== #
drop table if exists member;
create table member(
	mno int auto_increment primary key,				# 회원 번호
    id varchar(10) not null unique,					# 회원 아이디
    pw varchar(20) not null,						# 회원 비밀번호
    name varchar(10) not null,						# 회원 이름
    phone varchar(14) not null unique,				# 회원 전화번호
    email varchar(30) unique,						# 회원 이메일
    address varchar(100),							# 회원 주소
    birth varchar(8) not null						# 회원 생년월일
);
select * from member;

# ================== 강의실 ================== #
drop table if exists classroom;
create table classroom(
	rno int auto_increment primary key,				# 강의실 번호
    roomnumber int not null unique,					# 강의실 호실
    totalperson int not null,						# 강의실 총 인원
    createdate datetime default now(),				# 강의실 등록 날짜
    updatedate datetime default now()				# 강의실 수정 날짜
);
select * from classroom;

# ================== 강의 시간 ================== #
drop table if exists classtime;
create table classtime(
	tno int auto_increment primary key,
	classno int,									# 강의 번호
    dayweek char(3) not null,						# 강의 요일
    starttime time not null,						# 강의 시작 시간
    endtime time not null,							# 강의 끝 시간
    foreign key(classno) references class(classno)
);
insert into classtime
select * from classtime;

# ================== 행정직원 ================== #
drop table if exists employee;
create table employee(
	eno int auto_increment primary key,				# 행정직원 번호
    grade varchar(5),								# 직원 등급
    department varchar(15) not null,				# 행정직원 부서
    salary bigint,									# 행정직원 급여
    foreign key(eno) references member(mno) on update cascade on delete cascade
);
select * from employee;

# ================== 교수 ================== #
drop table if exists professor;
create table professor(
	pno int auto_increment primary key,				# 교수 번호
    pgrade varchar(5) not null,						# 직원 등급
    psalary bigint,									# 교수 급여
    proom varchar(100),								# 교수 강의실 위치
	degree varchar(20) not null,					# 교수 학위
    majorpart varchar(15) not null,					# 교수 전공
	mainmajor varchar(20) not null,					# 교수 담당 학과
    foreign key(pno) references member(mno)
);
select * from professor;
select * from professor right outer join member on professor.pno = member.mno;

# ================== 학기 ================== #
drop table if exists season;
create table season(
	sno int auto_increment primary key,
    semester char(6) not null,									# 학기
    startdate date not null,									# 학기 시작 날짜
    enddate date not null										# 학기 끝 날짜
);
select * from season;

# ================== 강의 정보 ================== #
drop table if exists classinfo;
create table classinfo(
	no int auto_increment primary key,							# 
    classno int,												# 강의과목 번호 FK
    professor int,												# 강의교수 번호 FK			
	roomnumber int,												# 강의실 번호 FK
	tno int,													# 강의 시간 번호 FK
    sno int,													# 학기 번호 FK
    foreign key(classno) references class(classno),
    foreign key(professor) references member(mno),
    foreign key(roomnumber) references classroom(roomnumber),
    foreign key(classno) references classtime(classno),
    foreign key(sno) references season(sno)
);

select * from classinfo;
select * from classinfo right outer join class on classinfo.classno = class.classno;