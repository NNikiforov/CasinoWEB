-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema casinoDB
-- -----------------------------------------------------
-- Online игра: Пиковая дама (Штосс).
-- Азартная игра, которая берет свою основу у классического казино. Игра происходит между Игроком(Понтером) и ИИ(Банкомётом). Необходимо хранить информацию о Пользователях (Администатор, Игрок): Имя, Фамилия, Пол, Возраст, Логгин, Пароль. Каждому Игроку соответствует свой баланс и рейтинг, изменяющийся при Поражении/Победе. Администратор может управлять Игроками(например заблокировать при отрицательном балансе).
-- Пользователи системы могут посылать друг другу сообщения, а также отвечать на них.
-- Пример игры: http://onlineguru.ru/1517/view.html
-- Правила игры: «Понтёры из своих колод выбирают карту, на которую делают ставку, и банкомёт начинает промётывать свою колоду направо и налево. Если карта понтёра легла налево от банкомёта, то выиграл понтёр, если направо — то банкомёт. При совпадении масти в случае выигрыша, ставка игрока увеличивается на половину, в случае проигрыша — на половину уменьшается. Если масти не совпадаю, ставка остаётся неизменной. Если на оба бокса дилера попадают одинаковые карты, то такая ситуация называется в стосе Plié (плие). Поскольку нечётная карта появляется на столе первой, игрок проигрывает ставку на карту.»

-- -----------------------------------------------------
-- Schema casinoDB
--
-- Online игра: Пиковая дама (Штосс).
-- Азартная игра, которая берет свою основу у классического казино. Игра происходит между Игроком(Понтером) и ИИ(Банкомётом). Необходимо хранить информацию о Пользователях (Администатор, Игрок): Имя, Фамилия, Пол, Возраст, Логгин, Пароль. Каждому Игроку соответствует свой баланс и рейтинг, изменяющийся при Поражении/Победе. Администратор может управлять Игроками(например заблокировать при отрицательном балансе).
-- Пользователи системы могут посылать друг другу сообщения, а также отвечать на них.
-- Пример игры: http://onlineguru.ru/1517/view.html
-- Правила игры: «Понтёры из своих колод выбирают карту, на которую делают ставку, и банкомёт начинает промётывать свою колоду направо и налево. Если карта понтёра легла налево от банкомёта, то выиграл понтёр, если направо — то банкомёт. При совпадении масти в случае выигрыша, ставка игрока увеличивается на половину, в случае проигрыша — на половину уменьшается. Если масти не совпадаю, ставка остаётся неизменной. Если на оба бокса дилера попадают одинаковые карты, то такая ситуация называется в стосе Plié (плие). Поскольку нечётная карта появляется на столе первой, игрок проигрывает ставку на карту.»
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `casinoDB` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin ;
SHOW WARNINGS;
USE `casinoDB` ;

-- -----------------------------------------------------
-- Table `casinoDB`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `casinoDB`.`users` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL COMMENT 'Имя пользователя.',
  `surname` VARCHAR(45) NOT NULL COMMENT 'Фамилия пользователя.',
  `age` INT UNSIGNED NOT NULL COMMENT 'Возраст пользователя.',
  `gender` ENUM('MALE', 'FEMALE') NOT NULL COMMENT 'Пол пользователя.',
  `login` VARCHAR(254) NOT NULL COMMENT 'Логин пользователя.',
  `password` CHAR(32) NOT NULL COMMENT 'Пароль. Будет храниться в закэшированном виде.',
  `role` ENUM('ADMIN', 'PUNTER') NOT NULL COMMENT 'Роль пользователя. Пунтер - игрок, который делает ставки. Админ - пользователь, осуществляющий управление игроками.',
  `cash` INT NULL DEFAULT 0 COMMENT 'Баланс понтера, может быть отрицательным. У Админов не определён. ',
  `rating` INT UNSIGNED NULL DEFAULT 0 COMMENT 'Рэйтинг - процент выигрышных ставок. При победе/поражении будет изменятся. У Админов не определён.',
  `is_blocked` TINYINT NULL DEFAULT 0 COMMENT 'Флаг показывающий, является ли пользователь заблокированным. Не определён у администраторов.',
  PRIMARY KEY (`id`),
  INDEX `age` (`age` ASC) VISIBLE,
  UNIQUE INDEX `loggin_UNIQUE` (`login` ASC) VISIBLE,
  INDEX `rating` (`rating` ASC) VISIBLE)
ENGINE = InnoDB
COMMENT = 'Таблица, которая содержит информацию о пользвателях системы.';

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `casinoDB`.`croupiers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `casinoDB`.`croupiers` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL COMMENT 'Имя банкомёта.',
  `yield` INT NOT NULL COMMENT 'Доходность банкомёта. Может быть отрицательной.',
  PRIMARY KEY (`id`),
  INDEX `yield` (`yield` ASC) VISIBLE)
ENGINE = InnoDB
COMMENT = 'Данная таблица содержит информацию о банкомётах.';

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `casinoDB`.`bets`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `casinoDB`.`bets` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `croupier_id` INT UNSIGNED NOT NULL COMMENT 'Внешний ключ на банкомёт.',
  `punter_id` INT UNSIGNED NOT NULL COMMENT 'Внешний ключ на понтера(тот, кто делает ставку).',
  `selected_card` CHAR(4) NOT NULL COMMENT 'Карта, на которую сделана ставка.',
  `amount` INT NOT NULL COMMENT 'Размер ставки.',
  `deck` VARCHAR(210) NOT NULL COMMENT 'Колода - последовательность карт, в которой банкомет будет метать карты направо и налево. Представленна как строка, состоящая из разделенных пробелом 52 элементов вида: \"D10\". Первый символ - масть карты, остальные значение карты. Данная сущность потребуется только в полном объеме, поэтому не будет нарушать 1НФ.',
  `time` DATETIME NOT NULL COMMENT 'Время, в которое была сделана ставка.',
  `result` ENUM('BIGWIN', 'WIN', 'LOSE', 'SLOSE') NOT NULL,
  `profit` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  INDEX `fk_rates_users_idx` (`punter_id` ASC) VISIBLE,
  INDEX `fk_rates_croupiers_idx` (`croupier_id` ASC) VISIBLE,
  INDEX `time` (`time` ASC) VISIBLE,
  INDEX `amount` (`amount` ASC) VISIBLE,
  CONSTRAINT `fk_rates_users`
    FOREIGN KEY (`punter_id`)
    REFERENCES `casinoDB`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_rates_croupiers`
    FOREIGN KEY (`croupier_id`)
    REFERENCES `casinoDB`.`croupiers` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'Таблица, содержащая все сделанные ставки понтеров.';

SHOW WARNINGS;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
