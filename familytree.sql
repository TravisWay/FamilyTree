-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `mydb` ;

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
SHOW WARNINGS;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`People`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`People` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `mydb`.`People` (
  `ID` INT NOT NULL,
  `fname` VARCHAR(45) NOT NULL,
  `lname` VARCHAR(45) NOT NULL,
  `age` INT NOT NULL,
  `mother_id` INT NOT NULL,
  `father_id` INT NOT NULL,
  `sibling_id` INT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `mydb`.`Tree`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Tree` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `mydb`.`Tree` (
  `branch` INT NOT NULL,
  PRIMARY KEY (`branch`),
  CONSTRAINT `fk_id1`
    FOREIGN KEY (`branch`)
    REFERENCES `mydb`.`People` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
SET SQL_MODE = '';
GRANT USAGE ON *.* TO ftuser@localhost;
 DROP USER ftuser@localhost;
SET SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';
SHOW WARNINGS;
CREATE USER 'ftuser'@'localhost' IDENTIFIED BY 'ftuser';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE `mydb`.* TO 'ftuser'@'localhost';
SHOW WARNINGS;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
