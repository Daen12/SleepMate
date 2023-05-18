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
`nickname` VARCHAR(20) NOT NULL,
PRIMARY KEY (`id`)
);

insert into `users` (id, password, name, nickname)
values ("ssafy", "1234", "김싸피", "왕십리 야식");


-- -----------------------------------------------------
-- Verify Table
-- -----------------------------------------------------
SELECT * FROM users;