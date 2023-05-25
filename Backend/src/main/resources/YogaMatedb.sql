DROP DATABASE IF EXISTS `ssafy_yoga`;
CREATE DATABASE IF NOT EXISTS `ssafy_yoga`;
USE `ssafy_yoga`;

-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `users` (
`id` VARCHAR(20) NOT NULL,
`password` VARCHAR(20) NOT NULL,
`name` VARCHAR(20) NOT NULL,
`nickname` VARCHAR(10) NOT NULL,
`prefer1` VARCHAR(20) NULL,
`prefer2` VARCHAR(20) NULL,
`prefer3` VARCHAR(20) NULL,
PRIMARY KEY (`id`)
);

insert into `users` (id, password, name, nickname)
values ("ssafy", "1234", "김싸피", "왕십리 야식");

-- -----------------------------------------------------
-- Table `board`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `board` (
`idx` BIGINT AUTO_INCREMENT,
`classnum` INT NOT NULL,
`title` VARCHAR(20) NOT NULL,
`content` VARCHAR(50) NOT NULL,
`writer` VARCHAR(10) NOT NULL,
`viewcnt` INT DEFAULT 0,
`regdate` TIMESTAMP DEFAULT now(),
PRIMARY KEY (`idx`)
);

insert into `board` (classnum, title, content, writer)
values (1, "게시판 테스트 제목입니다.", "게시판 테스트 내용입니다.", "왕십리 야식");

insert into `board` (classnum, title, content, writer)
values (1, "게시판 테스트 제목입니다2.", "게시판 테스트 내용입니다2.", "daen");

-- -----------------------------------------------------
-- Table `comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `comment` (
`idx` BIGINT AUTO_INCREMENT,
`articleIdx` BIGINT NOT NULL,
`content` VARCHAR(50) NOT NULL,
`writer` VARCHAR(10) NOT NULL,
`regdate` TIMESTAMP DEFAULT now(),
PRIMARY KEY (`idx`)
);

insert into `comment` (articleIdx, content, writer)
values (1, "댓글 테스트 내용입니다 ㅎㅎ", "ssafy");

-- -----------------------------------------------------
-- Table `commentAlert`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `commentAlert` (
`idx` BIGINT AUTO_INCREMENT,
`nickname` VARCHAR(20) NOT NULL,
`articleIdx` BIGINT NOT NULL,
`content` VARCHAR(50) NOT NULL,
`writer` VARCHAR(10) NOT NULL,
`regdate` TIMESTAMP DEFAULT now(),
PRIMARY KEY (`idx`)
);

-- insert into `commentAlert` (nickname, articleIdx, content, writer)
-- values ("Daen", 1, "잘 보고 갑니다", "Daen");

SELECT ca.articleIdx, b.title, ca.content, ca.writer, ca.regdate
FROM commentAlert AS ca
JOIN board AS b
ON ca.articleIdx = b.idx
WHERE nickname = 1234;
-- -----------------------------------------------------
-- Verify Table
-- -----------------------------------------------------
SELECT * FROM users;
SELECT * FROM board;
SELECT * FROM comment;
SELECT * FROM commentAlert;