

    create table saa_aviso (
        aviso_id bigint not null auto_increment,
        avi_fim datetime not null,
        avi_inicio datetime not null,
        avi_mensagem varchar(1000) not null,
        licenciado_sistema_id bigint not null,
        primary key (aviso_id)
    );

    create table saa_licenciado (
        licenciado_id bigint not null auto_increment unique,
        lic_cpfcnpj varchar(14) not null,
        lic_nome varchar(60) not null,
        primary key (licenciado_id)
    );

    create table saa_licenciado_sistema (
        licenciado_sistema_id bigint not null auto_increment,
        lic_sis_numero varchar(30) not null,
        lic_sis_tipo varchar(1) not null,
        lic_sis_validade datetime,
        licenciado_id bigint not null,
        sistema_id bigint not null,
        primary key (licenciado_sistema_id),
        unique (licenciado_id, sistema_id)
    );

    create table saa_perfil (
        perfil_id bigint not null auto_increment,
        perf_nome varchar(60) not null,
        sistema_id bigint not null,
        primary key (perfil_id)
    );

    create table saa_perfil_rotina (
        perfil_rotina_id bigint not null auto_increment unique,
        perfil_id bigint not null,
        rotina_id bigint not null,
        primary key (perfil_rotina_id),
        unique (perfil_id, rotina_id)
    );

    create table saa_rotina (
        rotina_id bigint not null auto_increment unique,
        rot_nome varchar(60) not null,
        rot_path varchar(60) not null,
        rot_status bit,
        sistema_id bigint not null,
        primary key (rotina_id)
    );

    create table saa_sistema (
        sistema_id bigint not null auto_increment,
        sis_descricao varchar(100),
        sis_nome varchar(60) not null unique,
        primary key (sistema_id)
    );

    create table saa_status (
        status_id bigint not null auto_increment,
        sta_nome varchar(60) not null unique,
        primary key (status_id)
    );

    create table saa_usuario (
        usuario_id bigint not null auto_increment unique,
        usu_cpf varchar(11) not null unique,
        usu_email varchar(60),
        usu_nome varchar(60) not null,
        usu_senha varchar(100),
        status_id bigint not null,
        primary key (usuario_id)
    );

    create table saa_usuario_perfil (
        usuario_perfil_id bigint not null auto_increment unique,
        usu_perf_data datetime not null,
        perfil_id bigint not null,
        usuario_id bigint not null,
        primary key (usuario_perfil_id),
        unique (usuario_id, perfil_id)
    );

    alter table saa_aviso 
        add index FKA9F6D8C4D2596064 (licenciado_sistema_id), 
        add constraint FKA9F6D8C4D2596064 
        foreign key (licenciado_sistema_id) 
        references saa_licenciado_sistema (licenciado_sistema_id);

    alter table saa_licenciado_sistema 
        add index FK99528006C29BF761 (licenciado_id), 
        add constraint FK99528006C29BF761 
        foreign key (licenciado_id) 
        references saa_licenciado (licenciado_id);

    alter table saa_licenciado_sistema 
        add index FK99528006FA8756D3 (sistema_id), 
        add constraint FK99528006FA8756D3 
        foreign key (sistema_id) 
        references saa_sistema (sistema_id);

    alter table saa_perfil 
        add index FKAD9147B8FA8756D3 (sistema_id), 
        add constraint FKAD9147B8FA8756D3 
        foreign key (sistema_id) 
        references saa_sistema (sistema_id);

    alter table saa_perfil_rotina 
        add index FKA4E4110C4112EEC1 (perfil_id), 
        add constraint FKA4E4110C4112EEC1 
        foreign key (perfil_id) 
        references saa_perfil (perfil_id);

    alter table saa_perfil_rotina 
        add index FKA4E4110CEB8B9421 (rotina_id), 
        add constraint FKA4E4110CEB8B9421 
        foreign key (rotina_id) 
        references saa_rotina (rotina_id);

    alter table saa_rotina 
        add index FKB188D891FA8756D3 (sistema_id), 
        add constraint FKB188D891FA8756D3 
        foreign key (sistema_id) 
        references saa_sistema (sistema_id);

    alter table saa_usuario 
        add index FK2522D2C2AF427501 (status_id), 
        add constraint FK2522D2C2AF427501 
        foreign key (status_id) 
        references saa_status (status_id);

    alter table saa_usuario_perfil 
        add index FK66455E921602B53 (usuario_id), 
        add constraint FK66455E921602B53 
        foreign key (usuario_id) 
        references saa_usuario (usuario_id);

    alter table saa_usuario_perfil 
        add index FK66455E94112EEC1 (perfil_id), 
        add constraint FK66455E94112EEC1 
        foreign key (perfil_id) 
        references saa_perfil (perfil_id);
