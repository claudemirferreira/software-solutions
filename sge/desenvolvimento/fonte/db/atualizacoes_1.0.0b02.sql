
-- Boletim: Questoes do boletim de Educ. Infantil
CREATE TABLE `sge`.`acad_questao_avaliacao` (
  `id_questao_avaliacao` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  `tx_questao` VARCHAR(80) NOT NULL,
  `tipo_fator` INT(1) UNSIGNED NOT NULL,
  PRIMARY KEY (`id_questao`)
)
ENGINE = InnoDB
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

