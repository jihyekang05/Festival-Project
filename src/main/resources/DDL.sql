
drop table member;

--CREATE TABLE MEMBER (
--	memberIndex	number(10)		primary key,
--	memberId	varchar2(200)		NOT NULL,
--	memberPw	varchar2(200)		NOT NULL,
--	memberBirth	date		NOT NULL,
--	memberAddr	varchar2(500)		NOT NULL,
--	memberEmail	varchar2(200)		NOT NULL,
--	memeber_phone	varchar2(100)		NOT NULL,
--	memberNickname	varchar2(50)		NOT NULL,
--	memberState	number(10)		NOT NULL,
--	interest_category VARCHAR2(100)
--);

CREATE TABLE MEMBER (
	memberIndex	number(10)		primary key,
	memberId	varchar2(200)		NOT NULL,
	memberPw	varchar2(200)		NOT NULL,
	memberBirth	date            NOT NULL,
	memberAddr	varchar2(500)		NOT NULL,
	memberEmail	varchar2(200)		NOT NULL,
	memberNickname	varchar2(50)        NOT NULL,
	memberState	number(10)		NOT NULL,
	memberCategory VARCHAR2(100)
);

drop table POSTS;
CREATE TABLE POSTS (
	postNum	number(10)		primary key,
	adminIndex	number(1)		NOT NULL,
	contentText	varchar2(4000)		NULL,
	reviewScoreAvg	number(1)		NULL,
	contentViews	number(10)		NULL,
	boardAddr	varchar2(2000)		NULL,
	boardLocAddr	number(10)		NULL,
	contentImage	varchar2(2000)		NULL,
	progressState	number(1)		NULL,
	festivalTitle	varchar2(1000)		NULL,
	festivalUploadDate	DATE		NULL,
    CONSTRAINT adminIndex FOREIGN KEY(adminIndex) REFERENCES admin(adminIndex),
    festivalCategory VARCHAR2(100),
);
drop table Favorite;
CREATE TABLE Favorite (
	memberIndex	number(10)		NOT NULL,
	postNum	number(10)		NOT NULL,
    CONSTRAINT member_favorite_index FOREIGN KEY(memberIndex) REFERENCES member(memberIndex),
    CONSTRAINT post_favorite_index FOREIGN KEY(postNum) REFERENCES POSTS(postNum)
);


ALTER TABLE "Favorite" ADD CONSTRAINT "PK_FAVORITE" PRIMARY KEY (
	"member_index",
	"post_num"
);

ALTER TABLE "Favorite" ADD CONSTRAINT "FK_MEMBER_TO_Favorite_1" FOREIGN KEY (
	"member_index"
);

ALTER TABLE "Favorite" ADD CONSTRAINT "FK_POSTS_TO_Favorite_1" FOREIGN KEY (
	"post_num"
);



drop table FESTIVAL_REVIEW;
CREATE TABLE FESTIVAL_REVIEW (
    reviewIndex number(10) primary key,
	postNum	number(10)		NOT NULL,
	memberIndex VARCHAR2(30) NOT NULL,
	reviewText	varchar2(1000)   NOT	NULL,
	reviewScore	number(1)		NOT NULL,
    CONSTRAINT postNum FOREIGN KEY(postNum) REFERENCES POSTS(postNum) on delete cascade

);
drop table NOTICE;
CREATE TABLE NOTICE (
	postNum	number(10)		primary key,
	adminIndex	number(1)		NOT NULL,
	contentTitle	varchar2(200)		NOT NULL,
	contentText	varchar2(4000)		NOT NULL,
    noticeDate DATE NOT NULL, //추가
    CONSTRAINT admin_notice_index FOREIGN KEY(adminIndex) REFERENCES admin(adminIndex)
);
drop table message;
CREATE TABLE message (
	id_chattingmessage	number(10)		primary key,
	memberIndex	number(10)		NOT NULL,
	chattingroom_id	number(10)		NOT NULL,
	message	varchar2(50)		NULL,
	message_created_date	date		NULL,
	adminIndex	number(1)		NOT NULL
);
drop table chatting_ROOM;
CREATE TABLE chatting_ROOM (
	chattingroom_id	number(10)		primary key,
	postNum	number(10)		NOT NULL,
	adminIndex	number(1)		NOT NULL
);
drop table ADMIN;
CREATE TABLE ADMIN (
	adminIndex	number(10)		primary key,
	adminId	varchar2(100)		NOT NULL,
	adminPw	varchar2(100)		NOT NULL
);

DROP TABLE CATEGORY;
CREATE TABLE CATEGORY(
    interest_code Number(10) primary key,
    interest_text VARCHAR2(100)
);
--추가
DROP TABLE CONTACT;
CREATE TABLE CONTACT (
    contactIndex NUMBER(10) primary key,
    contactName VARCHAR2(100),
    contactEmail VARCHAR2(200),
--    contact_number VARCHAR2(200),
    contactText VARCHAR2(400)

);
