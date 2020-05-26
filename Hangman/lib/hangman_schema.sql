-- MySQL Script generated by MySQL Workbench
-- Tue May 26 19:17:46 2020
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema hangman
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `hangman` ;

-- -----------------------------------------------------
-- Schema hangman
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `hangman` DEFAULT CHARACTER SET utf8 ;
USE `hangman` ;

-- -----------------------------------------------------
-- Table `hangman`.`category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hangman`.`category` ;

CREATE TABLE IF NOT EXISTS `hangman`.`category` (
  `idCategory` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idCategory`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;

CREATE UNIQUE INDEX `name_UNIQUE` ON `hangman`.`category` (`name` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `hangman`.`guessword`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hangman`.`guessword` ;

CREATE TABLE IF NOT EXISTS `hangman`.`guessword` (
  `idGuessWord` INT NOT NULL AUTO_INCREMENT,
  `word` VARCHAR(45) NOT NULL,
  `idCategory` INT NOT NULL,
  PRIMARY KEY (`idGuessWord`, `idCategory`),
  CONSTRAINT `fk_GuessWord_Category`
    FOREIGN KEY (`idCategory`)
    REFERENCES `hangman`.`category` (`idCategory`))
ENGINE = InnoDB
AUTO_INCREMENT = 36
DEFAULT CHARACTER SET = utf8;

CREATE INDEX `fk_GuessWord_Category_idx` ON `hangman`.`guessword` (`idCategory` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `hangman`.`player`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hangman`.`player` ;

CREATE TABLE IF NOT EXISTS `hangman`.`player` (
  `idPlayer` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `highscore` INT NULL DEFAULT NULL,
  PRIMARY KEY (`idPlayer`))
ENGINE = InnoDB
AUTO_INCREMENT = 176
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;