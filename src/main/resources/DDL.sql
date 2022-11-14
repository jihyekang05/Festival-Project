
drop table member;

--CREATE TABLE MEMBER (
--	member_index	number(10)		primary key,
--	member_id	varchar2(200)		NOT NULL,
--	member_pw	varchar2(200)		NOT NULL,
--	member_birth	date		NOT NULL,
--	member_addr	varchar2(500)		NOT NULL,
--	member_email	varchar2(200)		NOT NULL,
--	memeber_phone	varchar2(100)		NOT NULL,
--	member_nickname	varchar2(50)		NOT NULL,
--	member_state	number(10)		NOT NULL,
--	interest_category VARCHAR2(100)
--);

CREATE TABLE MEMBER (
	member_index	number(10)		primary key,
	member_id	varchar2(200)		NOT NULL,
	member_pw	varchar2(200)		NOT NULL,
	member_birth	date            NOT NULL,
	member_addr	varchar2(500)		NOT NULL,
	member_email	varchar2(200)		NOT NULL,
	member_nickname	varchar2(50)        NOT NULL,
	member_state	number(10)		NOT NULL,
	member_category VARCHAR2(100)
);

drop table POSTS;
CREATE TABLE POSTS (
	post_num	number(10)		primary key,
	admin_index	number(1)		NOT NULL,
	content_text	varchar2(4000)		NULL,
	review_score_avg	number(1)		NULL,
	content_views	number(10)		NULL,
	board_addr	varchar2(2000)		NULL,
	board_loc_addr	number(10)		NULL,
	content_image	varchar2(2000)		NULL,
	progress_state	number(1)		NULL,
	festival_title	varchar2(1000)		NULL,
	festival_upload_date	DATE		NULL,
    CONSTRAINT admin_index FOREIGN KEY(admin_index) REFERENCES admin(admin_index),
    festival_category VARCHAR2(100),
);
drop table Favorite;
CREATE TABLE Favorite (
	member_index	number(10)		NOT NULL,
	post_num	number(10)		NOT NULL,
    CONSTRAINT member_favorite_index FOREIGN KEY(member_index) REFERENCES member(member_index),
    CONSTRAINT post_favorite_index FOREIGN KEY(post_num) REFERENCES POSTS(post_num)
);
drop table FESTIVAL_REVIEW;
CREATE TABLE FESTIVAL_REVIEW (
    review_index number(10) primary key,
	post_num	number(10)		NOT NULL,
	member_index	number(10)		NOT NULL,
	review_text	varchar(1000)   NOT	NULL,
	review_score	number(1)		NOT NULL,
    CONSTRAINT post_num FOREIGN KEY(post_num) REFERENCES POSTS(post_num) on delete cascade,
    CONSTRAINT member_review_index FOREIGN KEY(member_index) REFERENCES member(member_index)

);
drop table NOTICE;
CREATE TABLE NOTICE (
	post_num	number(10)		primary key,
	admin_index	number(1)		NOT NULL,
	content_title	varchar2(200)		NOT NULL,
	content_text	varchar2(4000)		NOT NULL,
    CONSTRAINT admin_notice_index FOREIGN KEY(admin_index) REFERENCES admin(admin_index)
);
drop table message;
CREATE TABLE message (
	id_chattingmessage	number(10)		primary key,
	member_index	number(10)		NOT NULL,
	chattingroom_id	number(10)		NOT NULL,
	message	varchar2(50)		NULL,
	message_created_date	date		NULL,
	admin_index	number(1)		NOT NULL
);
drop table chatting_ROOM;
CREATE TABLE chatting_ROOM (
	chattingroom_id	number(10)		primary key,
	post_num	number(10)		NOT NULL,
	admin_index	number(1)		NOT NULL
);
drop table ADMIN;
CREATE TABLE ADMIN (
	admin_index	number(10)		primary key,
	admin_id	varchar2(100)		NOT NULL,
	admin_pw	varchar2(100)		NOT NULL
);

DROP TABLE CATEGORY;
CREATE TABLE CATEGORY(
    interest_code Number(10) primary key,
    interest_text VARCHAR2(100)
);

