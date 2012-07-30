-- ============================================================== --
--             -- ======================== --                     --
--             -- Início VERSÃO 1.0.1a01  --                     --
--             -- ======================== --                     --
-- ============================================================== --

--=======================================================================--

ALTER TABLE authenticator.responsabilidade RENAME cs_ativo TO ativo;

CREATE INDEX ak_tx_grupo ON authenticator.grupo USING btree (lower(tx_grupo::text));

ALTER TABLE authenticator.usuario_responsa_designada ADD COLUMN id_sistema integer NOT NULL;
ALTER TABLE authenticator.usuario_responsa_designada ADD CONSTRAINT id_sistema_fkey FOREIGN KEY (id_sistema) REFERENCES authenticator.sistema (id_sistema) ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE authenticator.responsabilidade_servico ADD COLUMN tx_pacote_codigo character varying(20) NOT NULL;
