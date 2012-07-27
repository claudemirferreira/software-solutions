-- ============================================================== --
--             -- ======================== --                     --
--             -- Início VERSÃO 1.0.1a01  --                     --
--             -- ======================== --                     --
-- ============================================================== --

--=======================================================================--

ALTER TABLE authenticator.responsabilidade RENAME cs_ativo TO ativo;

CREATE INDEX ak_tx_grupo ON authenticator.grupo USING btree (lower(tx_grupo::text));