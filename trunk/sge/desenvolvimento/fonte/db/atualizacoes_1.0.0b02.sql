
-- Boletim: Questoes do boletim de Educ. Infantil
CREATE TABLE `sge`.`acad_questao_avaliacao` (
  `id_questao_avaliacao` bigint(20) NOT NULL AUTO_INCREMENT,
  `tx_questao` VARCHAR(80) NOT NULL,
  `fator_avaliacao` INT(1) NOT NULL,
  PRIMARY KEY (`id_questao_avaliacao`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8,
COMMENT = 'Questões da avaliação do boletim de Educação Infantil';

-- inserts das questoes
INSERT INTO `sge`.`acad_questao_avaliacao` VALUES (1, 'Veste-se independentemente', 0);
INSERT INTO `sge`.`acad_questao_avaliacao` VALUES (2, 'Consegue fazer riscos entre duas linhas', 0);
INSERT INTO `sge`.`acad_questao_avaliacao` VALUES (3, 'Consegue colorir figuras simples', 0);
INSERT INTO `sge`.`acad_questao_avaliacao` VALUES (4, 'Consegue apanhar e atirar bola', 0);
INSERT INTO `sge`.`acad_questao_avaliacao` VALUES (5, 'Consegue deslocar-se com um só pé', 0);
INSERT INTO `sge`.`acad_questao_avaliacao` VALUES (6, 'Reconhece as partes do corpo ( cabeça, braços, mãos, pernas, etc)', 0);
INSERT INTO `sge`.`acad_questao_avaliacao` VALUES (7, 'Consegue identificar a maioria das letras do alfabeto', 1);
INSERT INTO `sge`.`acad_questao_avaliacao` VALUES (8, 'Consegue identificar os números de 0 a 9', 1);
INSERT INTO `sge`.`acad_questao_avaliacao` VALUES (9, 'Reconhece a maioria das cores', 1);
INSERT INTO `sge`.`acad_questao_avaliacao` VALUES (10, 'Reconhece ( em cima, embaixo, ao lado, a frente, atrás)', 1);
INSERT INTO `sge`.`acad_questao_avaliacao` VALUES (11, 'Reconhece figuras geométricas (circulo, triangulo, quadrado)', 1);
INSERT INTO `sge`.`acad_questao_avaliacao` VALUES (12, 'Consegue fazer simples recado', 1);
INSERT INTO `sge`.`acad_questao_avaliacao` VALUES (13, 'Consegue seguir pequenas e simples orientações', 1);
INSERT INTO `sge`.`acad_questao_avaliacao` VALUES (14, 'Trabalha independentemente em tarefas simples', 1);
INSERT INTO `sge`.`acad_questao_avaliacao` VALUES (15, 'Tem boa relação com crianças de sua idade', 1);
INSERT INTO `sge`.`acad_questao_avaliacao` VALUES (16, 'É pontual', 1);
INSERT INTO `sge`.`acad_questao_avaliacao` VALUES (17, 'Coopera para higiene pessoal', 1);
INSERT INTO `sge`.`acad_questao_avaliacao` VALUES (18, 'Participa de trabalhos e brincadeiras em grupos', 1);
INSERT INTO `sge`.`acad_questao_avaliacao` VALUES (19, 'Sou Alegre', 2);
INSERT INTO `sge`.`acad_questao_avaliacao` VALUES (20, 'Sou agressivo', 2);
INSERT INTO `sge`.`acad_questao_avaliacao` VALUES (21, 'Exijo atenção constante', 2);
INSERT INTO `sge`.`acad_questao_avaliacao` VALUES (22, 'Sei ouvir com atenção', 2);
INSERT INTO `sge`.`acad_questao_avaliacao` VALUES (23, 'Faço muitas perguntas', 2);


-- Add coluna tipo_curso
ALTER TABLE `sge`.`acad_curso` ADD COLUMN `tipo_curso` INT(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT 'Educ Inf=0; Fundamental = 1.' AFTER `valor`;


-- Add tabela para educacao infantil
CREATE TABLE `acad_avaliacao_educ_infantil` (
  `id_avaliacao_educacao_infantil` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_boletim` bigint(20) NOT NULL,
  `id_questao_avaliacao` bigint(20) NOT NULL,
  `status_disciplina` INT(1),
  `conceito_avaliacao_1` INT(1),
  `conceito_avaliacao_2` int(1),
  `conceito_avaliacao_3` INT(1),
  `conceito_avaliacao_4` INT(1),
  `conceito_geral` INT(1),
  PRIMARY KEY (`id_avaliacao_educacao_infantil`),
  CONSTRAINT `FK_acad_avaliacao_boletim` FOREIGN KEY (`id_boletim`) REFERENCES `acad_boletim` (`id_boletim`),
  CONSTRAINT `FK_acad_avaliacao_questao` FOREIGN KEY (`id_questao_avaliacao`) REFERENCES `acad_questao_avaliacao` (`id_questao_avaliacao`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;


