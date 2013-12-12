

INSERT INTO `ESPECIALIDADE_MEDICA` (`ID`, `DESCRICAO`) VALUES
(2, 'Cardiologia'),
(1, 'Pediatria');

-- --------------------------------------------------------

INSERT INTO `INSTITUICAO_MEDICA` (`ID`, `NOME_FANTASIA`, `RAZAO_SOCIAL`, `CNPJ`) VALUES
(1, 'Hospital Espanhol', 'Hospital Espanhol Ltda', '06752293000140'),
(2, 'Hospital Sao Carlos', 'Hospital Sao Carlos', '51381533000100');

-- --------------------------------------------------------

--
-- Dumping data for table `MEDICO`
--

INSERT INTO `MEDICO` (`ID`, `NOME`, `SOBRENOME`, `EMAIL`, `CURRICULO`, `TELEFONE`, `CRM`) VALUES
(1, 'Thiago', 'Pinto', 'trgpwild@gmail.com', 'Formado pela USP', '1133245566', 'CRM-SP 2233'),
(2, 'Camilo', 'Lopes', 'camilo.lopes@itscompany.com.br', 'Formado pela UNICAMP', '1158987598', 'CRM-BA 3698');

-- --------------------------------------------------------
--
-- Dumping data for table `MEDICO_HAS_ESPECIALIDADE_MEDICA`
--

INSERT INTO `MEDICO_HAS_ESPECIALIDADE_MEDICA` (`MEDICO_ID`, `ESPECIALIDADE_MEDICA_ID`) VALUES
(1, 1),
(1, 2),
(2, 2);

-- --------------------------------------------------------
