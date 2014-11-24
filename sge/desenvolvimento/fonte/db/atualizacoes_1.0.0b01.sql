

DROP TABLE IF EXISTS `acad_media_turma`;
CREATE TABLE `acad_media_turma` (
  `id_media_turma` bigint(20) NOT NULL AUTO_INCREMENT,
  `media1` float NULL,
  `media2` float NULL,
  `media3` float NULL,
  `media4` float NULL,
  `disciplina` varchar(30) NOT NULL,
  PRIMARY KEY (`id_media_turma`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;