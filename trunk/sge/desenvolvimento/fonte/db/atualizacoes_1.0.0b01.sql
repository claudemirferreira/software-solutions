
-- Adicionado  campo acad_mensalidade.id_usuario_atualizacao
ALTER TABLE `sge`.`acad_mensalidade` ADD COLUMN `id_usuario_atualizacao` BIGINT AFTER `id_usuario`,
 ADD CONSTRAINT `FK_id_usuario_atualizacao` FOREIGN KEY `FK_id_usuario_atualizacao` (`id_usuario_atualizacao`)
    REFERENCES `saa_usuario` (`id_usuario`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT;