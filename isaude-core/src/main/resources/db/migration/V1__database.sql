SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

DROP SCHEMA IF EXISTS `camilo_itsisaude` ;
CREATE SCHEMA IF NOT EXISTS `camilo_itsisaude` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `camilo_itsisaude` ;

-- -----------------------------------------------------
-- Table `camilo_itsisaude`.`INSTITUICAO_MEDICA`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `camilo_itsisaude`.`INSTITUICAO_MEDICA` ;

CREATE  TABLE IF NOT EXISTS `camilo_itsisaude`.`INSTITUICAO_MEDICA` (
  `ID` INT NOT NULL AUTO_INCREMENT ,
  `NOME_FANTASIA` VARCHAR(80) NOT NULL ,
  `RAZAO_SOCIAL` VARCHAR(45) NOT NULL ,
  `CNPJ` VARCHAR(30) NOT NULL ,
  PRIMARY KEY (`ID`) ,
  UNIQUE INDEX `CNPJ_UNIQUE` (`CNPJ` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `camilo_itsisaude`.`MEDICO`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `camilo_itsisaude`.`MEDICO` ;

CREATE  TABLE IF NOT EXISTS `camilo_itsisaude`.`MEDICO` (
  `ID` INT NOT NULL AUTO_INCREMENT ,
  `NOME` VARCHAR(45) NOT NULL ,
  `SOBRENOME` VARCHAR(45) NOT NULL ,
  `EMAIL` VARCHAR(45) NOT NULL ,
  `CURRICULO` TEXT NULL ,
  `TELEFONE` VARCHAR(45) NOT NULL ,
  `CRM` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`ID`) ,
  UNIQUE INDEX `CRM_UNIQUE` (`CRM` ASC) ,
  UNIQUE INDEX `EMAIL_UNIQUE` (`EMAIL` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `camilo_itsisaude`.`ESPECIALIDADE_MEDICA`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `camilo_itsisaude`.`ESPECIALIDADE_MEDICA` ;

CREATE  TABLE IF NOT EXISTS `camilo_itsisaude`.`ESPECIALIDADE_MEDICA` (
  `ID` INT NOT NULL AUTO_INCREMENT ,
  `DESCRICAO` VARCHAR(80) NULL ,
  PRIMARY KEY (`ID`) ,
  UNIQUE INDEX `NOME_ESPECIALIDADE_UNIQUE` (`DESCRICAO` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `camilo_itsisaude`.`PACIENTE`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `camilo_itsisaude`.`PACIENTE` ;

CREATE  TABLE IF NOT EXISTS `camilo_itsisaude`.`PACIENTE` (
  `ID` INT NOT NULL AUTO_INCREMENT ,
  `NOME_COMPLETO` VARCHAR(45) NOT NULL ,
  `EMAIL` VARCHAR(45) NOT NULL ,
  `TELEFONE` VARCHAR(45) NOT NULL ,
  `CELULAR` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`ID`) ,
  UNIQUE INDEX `EMAIL_UNIQUE` (`EMAIL` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `camilo_itsisaude`.`EXPEDIENTE_MEDICO`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `camilo_itsisaude`.`EXPEDIENTE_MEDICO` ;

CREATE  TABLE IF NOT EXISTS `camilo_itsisaude`.`EXPEDIENTE_MEDICO` (
  `ID` INT NOT NULL ,
  `UNIDADE` VARCHAR(45) NULL ,
  `INTERVALO` INT NULL ,
  `HORARIO_INICIAL` TIME NULL ,
  `HORARIO_FINAL` TIME NULL ,
  `INSTITUICAO_MEDICA_ID` INT NOT NULL ,
  `MEDICO_ID` INT NOT NULL ,
  PRIMARY KEY (`ID`, `INSTITUICAO_MEDICA_ID`) ,
  INDEX `fk_EXPEDIENTE_MEDICO_INSTITUICAO_MEDICA1` (`INSTITUICAO_MEDICA_ID` ASC) ,
  INDEX `fk_EXPEDIENTE_MEDICO_MEDICO1` (`MEDICO_ID` ASC) ,
  CONSTRAINT `fk_EXPEDIENTE_MEDICO_INSTITUICAO_MEDICA1`
    FOREIGN KEY (`INSTITUICAO_MEDICA_ID` )
    REFERENCES `camilo_itsisaude`.`INSTITUICAO_MEDICA` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_EXPEDIENTE_MEDICO_MEDICO1`
    FOREIGN KEY (`MEDICO_ID` )
    REFERENCES `camilo_itsisaude`.`MEDICO` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `camilo_itsisaude`.`AGENDA_MEDICO`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `camilo_itsisaude`.`AGENDA_MEDICO` ;

CREATE  TABLE IF NOT EXISTS `camilo_itsisaude`.`AGENDA_MEDICO` (
  `ID` INT NOT NULL AUTO_INCREMENT ,
  `NOME_AGENDA` VARCHAR(45) NOT NULL ,
  `EXPEDIENTE_MEDICO_ID` INT NOT NULL ,
  PRIMARY KEY (`ID`, `EXPEDIENTE_MEDICO_ID`) ,
  UNIQUE INDEX `NOME_AGENDA_UNIQUE` (`NOME_AGENDA` ASC) ,
  INDEX `fk_AGENDA_MEDICO_EXPEDIENTE_MEDICO1` (`EXPEDIENTE_MEDICO_ID` ASC) ,
  CONSTRAINT `fk_AGENDA_MEDICO_EXPEDIENTE_MEDICO1`
    FOREIGN KEY (`EXPEDIENTE_MEDICO_ID` )
    REFERENCES `camilo_itsisaude`.`EXPEDIENTE_MEDICO` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `camilo_itsisaude`.`AGENDA_CONSULTA_MEDICA`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `camilo_itsisaude`.`AGENDA_CONSULTA_MEDICA` ;

CREATE  TABLE IF NOT EXISTS `camilo_itsisaude`.`AGENDA_CONSULTA_MEDICA` (
  `ID` INT NOT NULL AUTO_INCREMENT ,
  `DATA_CONSULTA` DATE NULL ,
  `HORARIO_CONSULTA` TIME NULL ,
  `AGENDA_MEDICO_ID` INT NOT NULL ,
  PRIMARY KEY (`ID`) ,
  INDEX `fk_AGENDA_DIA_HORARIO_MEDICO_AGENDA_MEDICO1` (`AGENDA_MEDICO_ID` ASC) ,
  CONSTRAINT `fk_AGENDA_DIA_HORARIO_MEDICO_AGENDA_MEDICO1`
    FOREIGN KEY (`AGENDA_MEDICO_ID` )
    REFERENCES `camilo_itsisaude`.`AGENDA_MEDICO` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `camilo_itsisaude`.`AGENDAMENTO_CONSULTA_MEDICA_PACIENTE`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `camilo_itsisaude`.`AGENDAMENTO_CONSULTA_MEDICA_PACIENTE` ;

CREATE  TABLE IF NOT EXISTS `camilo_itsisaude`.`AGENDAMENTO_CONSULTA_MEDICA_PACIENTE` (
  `ID` INT NOT NULL AUTO_INCREMENT ,
  `PACIENTE_ID` INT NOT NULL ,
  `AGENDA_CONSULTA_MEDICA_ID` INT NOT NULL ,
  PRIMARY KEY (`ID`) ,
  INDEX `fk_AGENDAMENTO_CONSULTA_MEDICA_PACIENTE1` (`PACIENTE_ID` ASC) ,
  INDEX `fk_AGENDAMENTO_CONSULTA_MEDICA_PACIENTE_AGENDA_CONSULTA_MEDICA1` (`AGENDA_CONSULTA_MEDICA_ID` ASC) ,
  CONSTRAINT `fk_AGENDAMENTO_CONSULTA_MEDICA_PACIENTE1`
    FOREIGN KEY (`PACIENTE_ID` )
    REFERENCES `camilo_itsisaude`.`PACIENTE` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_AGENDAMENTO_CONSULTA_MEDICA_PACIENTE_AGENDA_CONSULTA_MEDICA1`
    FOREIGN KEY (`AGENDA_CONSULTA_MEDICA_ID` )
    REFERENCES `camilo_itsisaude`.`AGENDA_CONSULTA_MEDICA` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `camilo_itsisaude`.`INSTITUICAO_MEDICA_HAS_MEDICO`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `camilo_itsisaude`.`INSTITUICAO_MEDICA_HAS_MEDICO` ;

CREATE  TABLE IF NOT EXISTS `camilo_itsisaude`.`INSTITUICAO_MEDICA_HAS_MEDICO` (
  `INSTITUICAO_MEDICA_ID` INT NOT NULL ,
  `MEDICO_ID` INT NOT NULL ,
  PRIMARY KEY (`INSTITUICAO_MEDICA_ID`, `MEDICO_ID`) ,
  INDEX `fk_INSTITUICAO_MEDICA_has_MEDICO_MEDICO1` (`MEDICO_ID` ASC) ,
  INDEX `fk_INSTITUICAO_MEDICA_has_MEDICO_INSTITUICAO_MEDICA1` (`INSTITUICAO_MEDICA_ID` ASC) ,
  CONSTRAINT `fk_INSTITUICAO_MEDICA_has_MEDICO_INSTITUICAO_MEDICA1`
    FOREIGN KEY (`INSTITUICAO_MEDICA_ID` )
    REFERENCES `camilo_itsisaude`.`INSTITUICAO_MEDICA` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_INSTITUICAO_MEDICA_has_MEDICO_MEDICO1`
    FOREIGN KEY (`MEDICO_ID` )
    REFERENCES `camilo_itsisaude`.`MEDICO` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `camilo_itsisaude`.`MEDICO_HAS_ESPECIALIDADE_MEDICA`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `camilo_itsisaude`.`MEDICO_HAS_ESPECIALIDADE_MEDICA` ;

CREATE  TABLE IF NOT EXISTS `camilo_itsisaude`.`MEDICO_HAS_ESPECIALIDADE_MEDICA` (
  `MEDICO_ID` INT NOT NULL ,
  `ESPECIALIDADE_MEDICA_ID` INT NOT NULL ,
  PRIMARY KEY (`MEDICO_ID`, `ESPECIALIDADE_MEDICA_ID`) ,
  INDEX `fk_MEDICO_has_ESPECIALIDADE_MEDICA_ESPECIALIDADE_MEDICA1` (`ESPECIALIDADE_MEDICA_ID` ASC) ,
  INDEX `fk_MEDICO_has_ESPECIALIDADE_MEDICA_MEDICO1` (`MEDICO_ID` ASC) ,
  CONSTRAINT `fk_MEDICO_has_ESPECIALIDADE_MEDICA_MEDICO1`
    FOREIGN KEY (`MEDICO_ID` )
    REFERENCES `camilo_itsisaude`.`MEDICO` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_MEDICO_has_ESPECIALIDADE_MEDICA_ESPECIALIDADE_MEDICA1`
    FOREIGN KEY (`ESPECIALIDADE_MEDICA_ID` )
    REFERENCES `camilo_itsisaude`.`ESPECIALIDADE_MEDICA` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;