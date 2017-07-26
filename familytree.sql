-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema familytree
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `familytree` ;

-- -----------------------------------------------------
-- Schema familytree
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `familytree` DEFAULT CHARACTER SET utf8 ;
USE `familytree` ;

-- -----------------------------------------------------
-- Table `people`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `people` ;

CREATE TABLE IF NOT EXISTS `people` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `fname` VARCHAR(45) NOT NULL,
  `lname` VARCHAR(45) NOT NULL,
  `age` INT NOT NULL,
  `mother_id` INT NOT NULL,
  `father_id` INT NOT NULL,
  `sibling_id` INT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tree`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tree` ;

CREATE TABLE IF NOT EXISTS `tree` (
  `branch` INT NOT NULL,
  PRIMARY KEY (`branch`),
  CONSTRAINT `fk_id1`
    FOREIGN KEY (`branch`)
    REFERENCES `people` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
GRANT USAGE ON *.* TO ftuser@localhost;
 DROP USER ftuser@localhost;
SET SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';
CREATE USER 'ftuser'@'localhost' IDENTIFIED BY 'ftuser';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'ftuser'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
