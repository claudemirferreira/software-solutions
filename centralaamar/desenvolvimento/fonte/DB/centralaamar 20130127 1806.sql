-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.37-community-nt


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema centralaamar
--

CREATE DATABASE IF NOT EXISTS centralaamar;
USE centralaamar;

--
-- Definition of table `grupo`
--

DROP TABLE IF EXISTS `grupo`;
CREATE TABLE `grupo` (
  `grup_id` int(11) NOT NULL auto_increment,
  `nome` varchar(60) NOT NULL,
  PRIMARY KEY  (`grup_id`),
  UNIQUE KEY `grup_id` (`grup_id`),
  UNIQUE KEY `nome` (`nome`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `grupo`
--

/*!40000 ALTER TABLE `grupo` DISABLE KEYS */;
/*!40000 ALTER TABLE `grupo` ENABLE KEYS */;


--
-- Definition of table `grupo_membro`
--

DROP TABLE IF EXISTS `grupo_membro`;
CREATE TABLE `grupo_membro` (
  `grupo_grup_id` int(11) NOT NULL,
  `membros_mem_id` int(11) NOT NULL,
  UNIQUE KEY `membros_mem_id` (`membros_mem_id`),
  KEY `FK55A764C0720455A9` (`membros_mem_id`),
  KEY `FK55A764C0D357F303` (`grupo_grup_id`),
  CONSTRAINT `FK55A764C0D357F303` FOREIGN KEY (`grupo_grup_id`) REFERENCES `grupo` (`grup_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `grupo_membro`
--

/*!40000 ALTER TABLE `grupo_membro` DISABLE KEYS */;
/*!40000 ALTER TABLE `grupo_membro` ENABLE KEYS */;


--
-- Definition of table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hibernate_sequence`
--

/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` (`next_val`) VALUES 
 (1);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;


--
-- Definition of table `local`
--

DROP TABLE IF EXISTS `local`;
CREATE TABLE `local` (
  `loc_id` int(11) NOT NULL auto_increment,
  `nome` varchar(60) NOT NULL,
  PRIMARY KEY  (`loc_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `local`
--

/*!40000 ALTER TABLE `local` DISABLE KEYS */;
/*!40000 ALTER TABLE `local` ENABLE KEYS */;


--
-- Definition of table `membro`
--

DROP TABLE IF EXISTS `membro`;
CREATE TABLE `membro` (
  `mem_id` int(11) NOT NULL auto_increment,
  `nome` varchar(100) default NULL,
  `data_nascimento` datetime default NULL,
  `email` varchar(50) default NULL,
  `sexo` tinyint(1) default NULL,
  `mae` varchar(100) default NULL,
  `pai` varchar(100) default NULL,
  `tem_filho` tinyint(1) default NULL,
  `endereco` varchar(200) default NULL,
  `bairro` varchar(30) default NULL,
  `celular` varchar(10) default NULL,
  `fone_comercial` varchar(10) default NULL,
  `fone_residencial` varchar(10) default NULL,
  `interesse` varchar(50) default NULL,
  `batizado` tinyint(1) default NULL,
  `data_batismo` datetime default NULL,
  `membro_igreja` tinyint(1) default NULL,
  `membros_batizado_familia` varchar(255) NOT NULL,
  `modo_conversao` smallint(5) unsigned default NULL,
  `amigo_contato` varchar(255) default NULL,
  `past_id` int(11) default NULL,
  `peq_id` int(11) default NULL,
  `pro_id` int(11) default NULL,
  PRIMARY KEY  (`mem_id`),
  UNIQUE KEY `mem_id` (`mem_id`),
  KEY `FKBFC28C2A9EADD421` (`past_id`),
  KEY `FKBFC28C2ADC39DE7` (`pro_id`),
  KEY `FKBFC28C2A24A1375E` (`peq_id`),
  CONSTRAINT `FKBFC28C2A24A1375E` FOREIGN KEY (`peq_id`) REFERENCES `pequenogrupo` (`peq_id`),
  CONSTRAINT `FKBFC28C2A9EADD421` FOREIGN KEY (`past_id`) REFERENCES `pastor` (`past_id`),
  CONSTRAINT `FKBFC28C2ADC39DE7` FOREIGN KEY (`pro_id`) REFERENCES `profissao` (`pro_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `membro`
--

/*!40000 ALTER TABLE `membro` DISABLE KEYS */;
/*!40000 ALTER TABLE `membro` ENABLE KEYS */;


--
-- Definition of table `pastor`
--

DROP TABLE IF EXISTS `pastor`;
CREATE TABLE `pastor` (
  `past_id` int(11) NOT NULL auto_increment,
  `nome` varchar(100) NOT NULL,
  PRIMARY KEY  (`past_id`),
  UNIQUE KEY `past_id` (`past_id`),
  UNIQUE KEY `nome` (`nome`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pastor`
--

/*!40000 ALTER TABLE `pastor` DISABLE KEYS */;
INSERT INTO `pastor` (`past_id`,`nome`) VALUES 
 (22,'ADAMOR PIMENTA'),
 (25,'ALIJOFRAM'),
 (7,'ALUIZIO GABRIEL'),
 (4,'ARLINDO PACHECO'),
 (20,'ARMANDO GONDIM'),
 (8,'ARTUR MANSK'),
 (28,'CARLOS BODAS'),
 (2,'CARLOS RIBEIRO'),
 (3,'DAVI RIBEIRO'),
 (9,'DAVID SOARES'),
 (13,'DOMINGOS'),
 (23,'ELIZEU PONTE DA SILVA'),
 (32,'ESTEVES'),
 (14,'FLAVIO COMPUS'),
 (10,'GILENO'),
 (34,'JOÃO BATISTA'),
 (5,'JOÃO VIEGAS'),
 (18,'JONAS CHOUA'),
 (27,'JOSÉ CLODOALDO'),
 (17,'JOSÉ MARTINS'),
 (33,'KUARUP REIS'),
 (36,'LORIVAL GOMES'),
 (11,'LUIZ FUCKNER'),
 (29,'MARCOS BENTES'),
 (16,'MOISÉS SEIXAS'),
 (30,'NATERCIO UCHOA'),
 (21,'NATERIN'),
 (19,'NILSON'),
 (1,'NOBERTO BRABO'),
 (12,'RAIMUNDO LIMA'),
 (26,'REBMATI'),
 (15,'ROGELHO ROJAS'),
 (6,'RUBENS FARIAS'),
 (31,'VALTER'),
 (24,'WALDELICIO'),
 (35,'WILL');
/*!40000 ALTER TABLE `pastor` ENABLE KEYS */;


--
-- Definition of table `pequenogrupo`
--

DROP TABLE IF EXISTS `pequenogrupo`;
CREATE TABLE `pequenogrupo` (
  `peq_id` int(11) NOT NULL auto_increment,
  `nome` varchar(100) NOT NULL,
  PRIMARY KEY  (`peq_id`),
  UNIQUE KEY `peq_id` (`peq_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pequenogrupo`
--

/*!40000 ALTER TABLE `pequenogrupo` DISABLE KEYS */;
INSERT INTO `pequenogrupo` (`peq_id`,`nome`) VALUES 
 (1,'PREPARA TE PARA ETERNIDADE'),
 (2,'MANANCIAL DE BENÇÃO'),
 (3,'BBBB');
/*!40000 ALTER TABLE `pequenogrupo` ENABLE KEYS */;


--
-- Definition of table `pergunta`
--

DROP TABLE IF EXISTS `pergunta`;
CREATE TABLE `pergunta` (
  `perg_id` int(11) NOT NULL auto_increment,
  `descricao` varchar(255) default NULL,
  PRIMARY KEY  (`perg_id`),
  UNIQUE KEY `perg_id` (`perg_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pergunta`
--

/*!40000 ALTER TABLE `pergunta` DISABLE KEYS */;
/*!40000 ALTER TABLE `pergunta` ENABLE KEYS */;


--
-- Definition of table `profissao`
--

DROP TABLE IF EXISTS `profissao`;
CREATE TABLE `profissao` (
  `pro_id` int(11) NOT NULL auto_increment,
  `nome` varchar(255) default NULL,
  PRIMARY KEY  (`pro_id`),
  UNIQUE KEY `pro_id` (`pro_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `profissao`
--

/*!40000 ALTER TABLE `profissao` DISABLE KEYS */;
INSERT INTO `profissao` (`pro_id`,`nome`) VALUES 
 (1,'ANALISTA DE SISTEMAS'),
 (2,'ADVOGADO(A)'),
 (3,'DESEMPREGADO(A)'),
 (4,'ESTUDANTE'),
 (5,'PROFESSOR(A)'),
 (6,'EMPRESÁRIO(A)'),
 (7,'AUTONOMO(A)'),
 (8,'APOSENTADO'),
 (9,'VIGILANTE'),
 (10,'VENDEDOR(A)'),
 (11,'TÉCNICO(A) EM ENFERMAGEM'),
 (12,'TÉCNICO(A) EM SEGURANÇA DO TRABALHO'),
 (13,'TÉCNICO REFRIGERAÇÃO E ELETRICA'),
 (14,'TÉCNICO DE URNA ELETRÔNICA'),
 (15,'ASSISTENTE SOCIAL'),
 (16,'FUNCIONÁRIO(A) PUBLICO FEDERAL'),
 (17,'DONA DE CASA'),
 (18,'COMPORTOR(A)'),
 (19,'COSTUREIRA'),
 (20,'ESTETICISTA'),
 (21,'COMERCIANTE'),
 (22,'BANCÁRIO'),
 (23,'SUPERVISOR(A) DE VENDAS'),
 (24,'FUNCIONÁRIO PRIVADO'),
 (25,'CONTADOR(A)'),
 (26,'ADMINISTRADOR(A)'),
 (27,'CIRUGIÃO DENTISTA'),
 (28,'PROJETISTA AUTONOMO'),
 (29,'TÉCNICO(A) EM INFORMÁTICA'),
 (30,'MOTORISTA'),
 (31,'INDÚSTRIARIO(A)'),
 (32,'FISIOTERAPEUTA'),
 (33,'PROFESSOR(A) DE EDUCAÇÃO FÍSICA'),
 (34,'ELETRICISTA'),
 (35,'MESTRE DE OBRAS'),
 (36,'MISSIONÁRIO'),
 (37,'PEDAGOGO(A)');
INSERT INTO `profissao` (`pro_id`,`nome`) VALUES 
 (38,'AUXILIAR ADMINISTRATIVO'),
 (39,'ANALISTA DE RH'),
 (40,'ASSESSOR JURÍDICO'),
 (41,'BIOLÓGO(A)'),
 (42,'MÚSICO'),
 (43,'CONSULTOR(A) EXECUTIVA'),
 (44,'ASSISTENTE ADMINISTRATIVO'),
 (45,'DETISTA'),
 (46,'TÉCNICO(A) AGRICOLA'),
 (47,'FUNCIONÁRIO(A) PÚBLICA'),
 (48,'SOCORRISTA'),
 (49,'XXXXX');
/*!40000 ALTER TABLE `profissao` ENABLE KEYS */;


--
-- Definition of table `questionario`
--

DROP TABLE IF EXISTS `questionario`;
CREATE TABLE `questionario` (
  `quest_id` int(11) NOT NULL auto_increment,
  `descricao` varchar(255) default NULL,
  `mem_id` int(11) default NULL,
  `peq_id` int(11) default NULL,
  `saba_id` int(11) default NULL,
  PRIMARY KEY  (`quest_id`),
  UNIQUE KEY `quest_id` (`quest_id`),
  KEY `FKF5C3A9FD24A1375E` (`peq_id`),
  KEY `FKF5C3A9FD417EDA29` (`saba_id`),
  KEY `FKF5C3A9FD88BC6E33` (`mem_id`),
  CONSTRAINT `FKF5C3A9FD24A1375E` FOREIGN KEY (`peq_id`) REFERENCES `pequenogrupo` (`peq_id`),
  CONSTRAINT `FKF5C3A9FD417EDA29` FOREIGN KEY (`saba_id`) REFERENCES `sabado` (`saba_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `questionario`
--

/*!40000 ALTER TABLE `questionario` DISABLE KEYS */;
/*!40000 ALTER TABLE `questionario` ENABLE KEYS */;


--
-- Definition of table `sabado`
--

DROP TABLE IF EXISTS `sabado`;
CREATE TABLE `sabado` (
  `saba_id` int(11) NOT NULL auto_increment,
  `data` datetime NOT NULL,
  PRIMARY KEY  (`saba_id`),
  UNIQUE KEY `saba_id` (`saba_id`),
  UNIQUE KEY `data` (`data`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sabado`
--

/*!40000 ALTER TABLE `sabado` DISABLE KEYS */;
/*!40000 ALTER TABLE `sabado` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
