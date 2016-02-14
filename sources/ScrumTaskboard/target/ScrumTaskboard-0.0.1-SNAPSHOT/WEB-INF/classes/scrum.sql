SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

DROP SCHEMA IF EXISTS `scrum` ;
CREATE SCHEMA IF NOT EXISTS `scrum` DEFAULT CHARACTER SET utf8 ;
USE `scrum` ;

-- -----------------------------------------------------
-- Table `scrum`.`burndownchart`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `scrum`.`burndownchart` ;

CREATE  TABLE IF NOT EXISTS `scrum`.`burndownchart` (
  `burndownChartId` INT(11) NOT NULL AUTO_INCREMENT ,
  `sprintDaysNr` INT(11) NULL DEFAULT NULL ,
  `storyPointsNr` FLOAT NULL DEFAULT NULL ,
  PRIMARY KEY (`burndownChartId`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `scrum`.`department`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `scrum`.`department` ;

CREATE  TABLE IF NOT EXISTS `scrum`.`department` (
  `departmentId` INT(11) NOT NULL AUTO_INCREMENT ,
  `departmentName` VARCHAR(255) NULL DEFAULT NULL ,
  `manager` VARCHAR(255) NULL DEFAULT NULL ,
  PRIMARY KEY (`departmentId`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `scrum`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `scrum`.`userList` ;

CREATE  TABLE IF NOT EXISTS `scrum`.`userList` (
  `userId` INT(11) NOT NULL AUTO_INCREMENT ,
  `password` VARCHAR(255) NULL DEFAULT NULL ,
  `position` VARCHAR(255) NULL DEFAULT NULL ,
  `username` VARCHAR(255) NULL DEFAULT NULL ,
  PRIMARY KEY (`userId`) )
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `scrum`.`developer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `scrum`.`developer` ;

CREATE  TABLE IF NOT EXISTS `scrum`.`developer` (
  `developerId` INT(11) NOT NULL AUTO_INCREMENT ,
  `firstName` VARCHAR(255) NULL DEFAULT NULL ,
  `lastName` VARCHAR(255) NULL DEFAULT NULL ,
  `departmentId` INT(11) NULL DEFAULT NULL ,
  `userId` INT(11) NOT NULL  ,
  `level`  VARCHAR(255) NULL DEFAULT NULL ,
  PRIMARY KEY (`developerId`) ,
  INDEX `FKFB30E7CA91040904` (`departmentId` ASC) ,
  INDEX `FKFB30E7CACA8F1776` (`userId` ASC) ,
  CONSTRAINT `FKFB30E7CACA8F1776`
    FOREIGN KEY (`userId` )
    REFERENCES `scrum`.`userList` (`userId` ),
  CONSTRAINT `FKFB30E7CA91040904`
    FOREIGN KEY (`departmentId` )
    REFERENCES `scrum`.`department` (`departmentId` ))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `scrum`.`taskboard`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `scrum`.`taskboard` ;

CREATE  TABLE IF NOT EXISTS `scrum`.`taskboard` (
  `taskboardId` INT(11) NOT NULL AUTO_INCREMENT ,
  `burndownChartId` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`taskboardId`) ,
  INDEX `FKAF06481A71178F0` (`burndownChartId` ASC) ,
  CONSTRAINT `FKAF06481A71178F0`
    FOREIGN KEY (`burndownChartId` )
    REFERENCES `scrum`.`burndownchart` (`burndownChartId` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `scrum`.`scrummaster`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `scrum`.`scrummaster` ;

CREATE  TABLE IF NOT EXISTS `scrum`.`scrummaster` (
  `scrumMasterId` INT(11) NOT NULL AUTO_INCREMENT ,
  `firstName` VARCHAR(255) NULL DEFAULT NULL ,
  `lastName` VARCHAR(255) NULL DEFAULT NULL ,
  `taskboardId` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`scrumMasterId`) ,
  INDEX `FKEA7F4D3CA454EB78` (`taskboardId` ASC) ,
  CONSTRAINT `FKEA7F4D3CA454EB78`
    FOREIGN KEY (`taskboardId` )
    REFERENCES `scrum`.`taskboard` (`taskboardId` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `scrum`.`story`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `scrum`.`story` ;

CREATE  TABLE IF NOT EXISTS `scrum`.`story` (
  `storyId` INT(11) NOT NULL AUTO_INCREMENT ,
  `description` VARCHAR(255) NULL DEFAULT NULL ,
  `taskboardId` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`storyId`) ,
  INDEX `FK68AF8F5A454EB78` (`taskboardId` ASC) ,
  CONSTRAINT `FK68AF8F5A454EB78`
    FOREIGN KEY (`taskboardId` )
    REFERENCES `scrum`.`taskboard` (`taskboardId` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `scrum`.`task`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `scrum`.`task` ;

CREATE  TABLE IF NOT EXISTS `scrum`.`task` (
  `taskId` INT(11) NOT NULL AUTO_INCREMENT ,
  `description` VARCHAR(255) NULL DEFAULT NULL ,
  `status` VARCHAR(255) NULL DEFAULT NULL ,
  `nrHours` INT(11) NULL DEFAULT NULL ,
  `priority` INT(11) NULL DEFAULT NULL ,
  `difficulty` INT(11) NULL DEFAULT NULL ,
  `developerId` INT(11) NULL DEFAULT NULL ,
  `storyId` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`taskId`) ,
  INDEX `FK36358576C243CA` (`developerId` ASC) ,
  INDEX `FK3635851FADCCE0` (`storyId` ASC) ,
  CONSTRAINT `FK3635851FADCCE0`
    FOREIGN KEY (`storyId` )
    REFERENCES `scrum`.`story` (`storyId` ),
  CONSTRAINT `FK36358576C243CA`
    FOREIGN KEY (`developerId` )
    REFERENCES `scrum`.`developer` (`developerId` ))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `scrum`.`comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `scrum`.`comment` ;

CREATE  TABLE IF NOT EXISTS `scrum`.`comment` (
   `commentId` INT(11) NOT NULL AUTO_INCREMENT ,
   `commentDescription` VARCHAR(255) NULL DEFAULT NULL,
    `taskId` INT(11) NULL DEFAULT NULL ,
    `storyPointsNr` INT(11) NULL DEFAULT NULL ,
    `developerId` INT(11) NULL DEFAULT NULL ,
    PRIMARY KEY (`commentId`),
  INDEX `FK3235251FA4CCE0` (`taskId` ASC) ,
  CONSTRAINT `FK3235251FA4CCE0`
    FOREIGN KEY (`taskId` )
    REFERENCES `scrum`.`task` (`taskId` ),
     INDEX `FK1238251FA4CCE0` (`developerId` ASC) ,
  CONSTRAINT `FK1238251FA4CCE0`
    FOREIGN KEY (`developerId` )
    REFERENCES `scrum`.`developer` (`developerId` ))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `scrum`.`history`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `scrum`.`history` ;

CREATE  TABLE IF NOT EXISTS `scrum`.`historyItem` (
   `historyItemId` INT(11) NOT NULL AUTO_INCREMENT ,
   `dayNr` INT(11) NULL DEFAULT NULL ,
   `storyPointsNr` FLOAT NULL DEFAULT NULL ,
   `burndownChartId` INT(11) NULL DEFAULT NULL ,
    PRIMARY KEY (`historyItemId`),
  INDEX `FK3237851FA4CCE0` (`burndownChartId` ASC) ,
  CONSTRAINT `FK3237851FA4CCE0`
    FOREIGN KEY (`burndownChartId` )
    REFERENCES `scrum`.`burndownChart` (`burndownChartId`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `scrum`.`metaTag`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `scrum`.`metaTag` ;

CREATE  TABLE IF NOT EXISTS `scrum`.`metaTag` (
   `metaTagId` INT(11) NOT NULL AUTO_INCREMENT ,
   `metaTagName` VARCHAR(255) NULL DEFAULT NULL,
    `taskId` INT(11) NULL DEFAULT NULL ,
    PRIMARY KEY (`metaTagId`),
  INDEX `FK3235251FA4AAE0` (`metaTagId` ASC) ,
  CONSTRAINT `FK3235252HA4CCE0`
    FOREIGN KEY (`taskId` )
    REFERENCES `scrum`.`task` (`taskId` ))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `scrum`.`skill`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `scrum`.`skill` ;

CREATE  TABLE IF NOT EXISTS `scrum`.`skill` (
   `skillId` INT(11) NOT NULL AUTO_INCREMENT ,
   `skillName` VARCHAR(255) NULL DEFAULT NULL,
    `percentage` FLOAT NULL DEFAULT NULL ,
    `developerId` INT(11) NULL DEFAULT NULL ,
    PRIMARY KEY (`skillId`),
  CONSTRAINT `FK4280251FA4CCE0`
    FOREIGN KEY (`developerId` )
    REFERENCES `scrum`.`developer` (`developerId` ))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `scrum`.`skillItem`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `scrum`.`skillItem` ;

CREATE  TABLE IF NOT EXISTS `scrum`.`skillItem` (
   `skillId` INT(11) NOT NULL AUTO_INCREMENT ,
   `skillName` VARCHAR(255) NULL DEFAULT NULL,
   `percentage` FLOAT NULL DEFAULT NULL ,
    PRIMARY KEY (`skillId`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
