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

-- Atualizacao no Servico --
DELETE FROM authenticator.responsabilidade  
ALTER TABLE authenticator.responsabilidade ADD COLUMN id_sistema integer NOT NULL;
ALTER TABLE authenticator.responsabilidade DROP CONSTRAINT responsabilidade_id_sistema_fkey;
ALTER TABLE authenticator.responsabilidade ADD CONSTRAINT responsabilidade_id_sistema_fkey FOREIGN KEY (id_sistema) REFERENCES authenticator.sistema (id_sistema) ON UPDATE NO ACTION ON DELETE NO ACTION;

DROP TABLE authenticator.servico;
CREATE TABLE authenticator.servico
(
  id_servico serial NOT NULL,
  tx_servico character varying(80) NOT NULL,
  tx_servico_codigo character varying(40) NOT NULL,
  tx_pacote_codigo character varying(20) NOT NULL,
  ativo boolean NOT NULL,
  CONSTRAINT servico_pkey PRIMARY KEY (id_servico)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE authenticator.servico OWNER TO root;

ALTER TABLE authenticator.responsabilidade_servico DROP COLUMN tx_servico_codigo;
ALTER TABLE authenticator.responsabilidade_servico DROP COLUMN tx_pacote_codigo;
ALTER TABLE authenticator.responsabilidade_servico DROP COLUMN tx_servico;
ALTER TABLE authenticator.responsabilidade_servico DROP COLUMN ativo;
ALTER TABLE authenticator.responsabilidade_servico ADD COLUMN id_servico integer NOT NULL;
ALTER TABLE authenticator.responsabilidade_servico ADD CONSTRAINT responsabilidade_servico_id_servico_fkey FOREIGN KEY (id_servico) REFERENCES authenticator.servico (id_servico) ON UPDATE NO ACTION ON DELETE NO ACTION;
---------
