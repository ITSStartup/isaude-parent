
--
-- Database: `camilo_itsisaude`
--


-- --------------------------------------------------------

INSERT INTO `ESPECIALIDADE_MEDICA` (`ID`, `DESCRICAO`) VALUES
(2, 'Cardiologia'),
(1, 'Pediatria');

-- --------------------------------------------------------
-

INSERT INTO `INSTITUICAO_MEDICA` (`ID`, `NOME_FANTASIA`, `RAZAO_SOCIAL`, `CNPJ`) VALUES
(1, 'Hospital Espanhol', 'Hospital Espanhol Ltda', '06752293000140'),
(2, 'Hospital Sao Carlos', 'Hospital Sao Carlos', '51381533000100');

-- --------------------------------------------------------

--
-- Dumping data for table `MEDICO`
--

INSERT INTO `MEDICO` (`ID`, `NOME`, `SOBRENOME`, `EMAIL`, `CURRICULO`, `TELEFONE`, `CRM`) VALUES
(1, 'Thiago', 'Pinto', 'trgpwild@gmail.com', 'Formado pela USP', '1133245566', 'CRM2233'),
(2, 'Camilo', 'Lopes', 'camilo.lopes@itscompany.com.br', 'Formado pela UNICAMP', '1158987598', 'CRM3698');

-- --------------------------------------------------------
--
-- Dumping data for table `MEDICO_HAS_ESPECIALIDADE_MEDICA`
--

INSERT INTO `MEDICO_HAS_ESPECIALIDADE_MEDICA` (`MEDICO_ID`, `ESPECIALIDADE_MEDICA_ID`) VALUES
(1, 1),
(1, 2),
(2, 2);

-- --------------------------------------------------------

--
-- Table structure for table `PACIENTE`
--

CREATE TABLE IF NOT EXISTS `PACIENTE` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NOME_COMPLETO` varchar(45) NOT NULL,
  `EMAIL` varchar(45) NOT NULL,
  `TELEFONE` varchar(45) NOT NULL,
  `CELULAR` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `EMAIL_UNIQUE` (`EMAIL`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `schema_version`
--

CREATE TABLE IF NOT EXISTS `schema_version` (
  `version_rank` int(11) NOT NULL,
  `installed_rank` int(11) NOT NULL,
  `version` varchar(50) NOT NULL,
  `description` varchar(200) NOT NULL,
  `type` varchar(20) NOT NULL,
  `script` varchar(1000) NOT NULL,
  `checksum` int(11) DEFAULT NULL,
  `installed_by` varchar(100) NOT NULL,
  `installed_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `execution_time` int(11) NOT NULL,
  `success` tinyint(1) NOT NULL,
  PRIMARY KEY (`version`),
  KEY `schema_version_vr_idx` (`version_rank`),
  KEY `schema_version_ir_idx` (`installed_rank`),
  KEY `schema_version_s_idx` (`success`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `schema_version`
--

INSERT INTO `schema_version` (`version_rank`, `installed_rank`, `version`, `description`, `type`, `script`, `checksum`, `installed_by`, `installed_on`, `execution_time`, `success`) VALUES
(1, 1, '1', 'database', 'SQL', 'V1__database.sql', 1914367176, 'camilo_admin', '2013-12-04 14:36:38', 5529, 1);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `AGENDAMENTO_CONSULTA_MEDICA_PACIENTE`
--
ALTER TABLE `AGENDAMENTO_CONSULTA_MEDICA_PACIENTE`
  ADD CONSTRAINT `fk_AGENDAMENTO_CONSULTA_MEDICA_PACIENTE1` FOREIGN KEY (`PACIENTE_ID`) REFERENCES `PACIENTE` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_AGENDAMENTO_CONSULTA_MEDICA_PACIENTE_AGENDA_CONSULTA_MEDICA1` FOREIGN KEY (`AGENDA_CONSULTA_MEDICA_ID`) REFERENCES `AGENDA_CONSULTA_MEDICA` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `AGENDA_CONSULTA_MEDICA`
--
ALTER TABLE `AGENDA_CONSULTA_MEDICA`
  ADD CONSTRAINT `fk_AGENDA_DIA_HORARIO_MEDICO_AGENDA_MEDICO1` FOREIGN KEY (`AGENDA_MEDICO_ID`) REFERENCES `AGENDA_MEDICO` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `AGENDA_MEDICO`
--
ALTER TABLE `AGENDA_MEDICO`
  ADD CONSTRAINT `fk_AGENDA_MEDICO_MEDICO` FOREIGN KEY (`MEDICO_ID`) REFERENCES `MEDICO` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_AGENDA_MEDICO_INSTITUICAO_MEDICA1` FOREIGN KEY (`INSTITUICAO_MEDICA_ID`) REFERENCES `INSTITUICAO_MEDICA` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
