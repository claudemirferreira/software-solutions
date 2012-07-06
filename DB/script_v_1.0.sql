
-- ======= Criacao do BD - versao inicial ================= -- 

-- Database: ss
-- DROP DATABASE ss;
CREATE DATABASE ss
  WITH OWNER = root
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'Portuguese, Brazil'
       LC_CTYPE = 'Portuguese, Brazil'
       CONNECTION LIMIT = -1;


-- DROP SCHEMA authenticator;
CREATE SCHEMA authenticator
  AUTHORIZATION root;

-- DROP TABLE authenticator.usuario;
CREATE TABLE authenticator.usuario
(
  id_usuario serial NOT NULL,
  tx_nome character varying(100) NOT NULL,
  tx_email character varying(60),
  tx_login character varying(11) NOT NULL,
  tx_senha character varying(100) NOT NULL,
  cs_status smallint NOT NULL,
  dt_cadastro timestamp without time zone NOT NULL,
  dt_ultimo_acesso timestamp with time zone NOT NULL,
  CONSTRAINT usuario_pkey PRIMARY KEY (id_usuario),
  CONSTRAINT usuario_tx_login_key UNIQUE (tx_login)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE authenticator.usuario OWNER TO root;


-- DROP TABLE authenticator.sistema;
CREATE TABLE authenticator.sistema
(
  id_sistema serial NOT NULL,
  tx_sistema character varying(50) NOT NULL,
  tx_descricao character varying(100),
  tx_sigla character varying(10) NOT NULL,
  tx_url_home character varying(50) NOT NULL,
  ativo boolean NOT NULL,
  CONSTRAINT sistema_pkey PRIMARY KEY (id_sistema),
  CONSTRAINT idx_sistema UNIQUE (tx_sistema)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE authenticator.sistema OWNER TO root;


-- DROP TABLE authenticator.perfil;
CREATE TABLE authenticator.perfil
(
  id_perfil serial NOT NULL,
  tx_perfil character varying(100),
  id_sistema integer NOT NULL,
  CONSTRAINT perfil_pkey PRIMARY KEY (id_perfil),
  CONSTRAINT id_sistema_fk FOREIGN KEY (id_sistema)
      REFERENCES authenticator.sistema (id_sistema) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT perfil_tx_perfil_key UNIQUE (tx_perfil)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE authenticator.perfil OWNER TO root;


-- DROP TABLE authenticator.servico;
CREATE TABLE authenticator.servico
(
  id_servico serial NOT NULL,
  id_sistema integer NOT NULL,
  cs_status boolean NOT NULL,
  tx_servico character varying(255) NOT NULL,
  CONSTRAINT servico_pkey PRIMARY KEY (id_servico),
  CONSTRAINT id_sistema_fkey FOREIGN KEY (id_sistema)
      REFERENCES authenticator.sistema (id_sistema) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE authenticator.servico OWNER TO root;


-- DROP TABLE authenticator.perfil_servico;
CREATE TABLE authenticator.perfil_servico
(
  id_perfil_servico serial NOT NULL,
  id_servico integer NOT NULL,
  id_perfil integer NOT NULL,
  cs_ativo boolean NOT NULL,
  CONSTRAINT servico_perfil_pkey PRIMARY KEY (id_perfil_servico),
  CONSTRAINT id_perfil_fkey FOREIGN KEY (id_perfil)
      REFERENCES authenticator.perfil (id_perfil) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT id_servico_fkey FOREIGN KEY (id_servico)
      REFERENCES authenticator.servico (id_servico) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT perfil_servico_idx UNIQUE (id_servico, id_perfil)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE authenticator.perfil_servico OWNER TO root;


-- DROP TABLE authenticator.usuario_perfil;
CREATE TABLE authenticator.usuario_perfil
(
  id_usuario_perfil serial NOT NULL,
  id_perfil integer NOT NULL,
  id_usuario integer NOT NULL,
  id_sistema integer NOT NULL,
  dt_inicial timestamp(0) without time zone NOT NULL,
  dt_final timestamp(0) without time zone,
  dt_criacao timestamp without time zone,
  CONSTRAINT usuario_perfil_pkey PRIMARY KEY (id_usuario_perfil),
  CONSTRAINT id_perfil_fkey FOREIGN KEY (id_perfil)
      REFERENCES authenticator.perfil (id_perfil) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT id_sistema_fkey FOREIGN KEY (id_sistema)
      REFERENCES authenticator.sistema (id_sistema) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT id_usuario_fkey FOREIGN KEY (id_usuario)
      REFERENCES authenticator.usuario (id_usuario) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT usuario_perfil_unique UNIQUE (id_usuario, id_sistema, id_perfil)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE authenticator.usuario_perfil OWNER TO root;



CREATE TABLE authenticator.responsabilidade
(
  id_responsabilidade serial NOT NULL,
  tx_responsabilidade character varying(100) NOT NULL,
  cs_ativo boolean NOT NULL,
  tx_descricao character varying(300) NOT NULL,
  CONSTRAINT responsabilidade_pkey PRIMARY KEY (id_responsabilidade)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE authenticator.responsabilidade OWNER TO root;


CREATE TABLE authenticator.responsabilidade_servico
(
  id_responsabilidade_servico serial NOT NULL,
  id_responsabilidade integer NOT NULL,
  tx_servico_codigo character varying(80) NOT NULL,
  dt_inicio timestamp without time zone NOT NULL,
  dt_fim timestamp without time zone,
  CONSTRAINT responsabilidade_servico_pkey PRIMARY KEY (id_responsabilidade_servico),
  CONSTRAINT responsabilidade_servico_id_responsabilidade_fkey FOREIGN KEY (id_responsabilidade)
      REFERENCES authenticator.responsabilidade (id_responsabilidade) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE authenticator.responsabilidade_servico OWNER TO root;

CREATE INDEX idx_fk_resp_servico_responsab
  ON authenticator.responsabilidade_servico
  USING btree
  (id_responsabilidade);



CREATE TABLE authenticator.perfil_responsabilidade
(
  id_perfil_responsabilidade serial NOT NULL,
  id_perfil integer NOT NULL,
  id_responsabilidade integer NOT NULL,
  dt_inicio timestamp without time zone NOT NULL,
  dt_termino timestamp without time zone,
  CONSTRAINT perfil_responsabilidade_pk PRIMARY KEY (id_perfil_responsabilidade),
  CONSTRAINT perfil_id_perfil_fk FOREIGN KEY (id_perfil)
      REFERENCES authenticator.perfil (id_perfil) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT perfil_id_responsabilidade_fk FOREIGN KEY (id_responsabilidade)
      REFERENCES authenticator.responsabilidade (id_responsabilidade) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE authenticator.perfil_responsabilidade OWNER TO root;

CREATE UNIQUE INDEX ak_perfil_responsabilidade
  ON authenticator.perfil_responsabilidade
  USING btree
  (id_responsabilidade, id_perfil);

CREATE INDEX idx_fk_perfil_id_responsabilidade
  ON authenticator.perfil_responsabilidade
  USING btree
  (id_responsabilidade);

CREATE INDEX idx_fk_perfil_resp_id_perfil
  ON authenticator.perfil_responsabilidade
  USING btree
  (id_perfil);

  
  
