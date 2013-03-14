
    create table cent_chamada (
        cham_id bigint not null auto_increment,
        presente bit,
        mem_id bigint not null,
        peq_id bigint not null,
        saba_id bigint not null,
        primary key (cham_id)
    );

    create table cent_escolaSabatina (
        esco_id bigint not null auto_increment,
        nome varchar(100) not null unique,
        primary key (esco_id)
    );

    create table cent_grupo (
        grup_id bigint not null auto_increment,
        nome varchar(60) not null unique,
        primary key (grup_id)
    );

    create table cent_grupo_cent_membro (
        cent_grupo_grup_id bigint not null,
        membros_mem_id bigint not null,
        unique (membros_mem_id)
    );

    create table cent_membro (
        mem_id bigint not null auto_increment unique,
        amigo_contato varchar(100),
        bairro varchar(30),
        batizado varchar(255),
        celular varchar(12),
        data_batismo date,
        data_nascimento date,
        email varchar(60),
        endereco varchar(200),
        fone_comercial varchar(12),
        fone_residencial varchar(12),
        interesse varchar(50),
        mae varchar(100),
        membro_igreja varchar(255) not null,
        membros_batizado_familia varchar(255),
        modo_conversao varchar(255),
        nome varchar(100) not null unique,
        pai varchar(100),
        sexo varchar(255) not null,
        tem_filho varchar(255),
        past_id bigint,
        peq_id bigint,
        pro_id bigint,
        primary key (mem_id),
        unique (pro_id)
    );

    create table cent_pastor (
        past_id bigint not null auto_increment,
        nome varchar(100) not null unique,
        primary key (past_id)
    );

    create table cent_pequenogrupo (
        peq_id bigint not null auto_increment,
        nome varchar(100) not null unique,
        anfi_id bigint,
        coord_id bigint,
        primary key (peq_id)
    );

    create table cent_pergunta (
        perg_id bigint not null auto_increment unique,
        nome varchar(100) not null unique,
        status bit not null,
        primary key (perg_id)
    );

    create table cent_profissao (
        pro_id bigint not null auto_increment,
        nome varchar(100) not null unique,
        primary key (pro_id)
    );

    create table cent_questionario (
        quest_id bigint not null auto_increment unique,
        nome varchar(255) not null unique,
        mem_id bigint not null,
        peq_id bigint not null,
        perg_id bigint not null,
        saba_id bigint not null,
        primary key (quest_id)
    );

    create table cent_sabado (
        saba_id bigint not null auto_increment,
        data datetime not null unique,
        primary key (saba_id)
    );

    create table portal.cent_cargo (
        carg_id bigint not null auto_increment,
        nome varchar(60) not null unique,
        primary key (carg_id)
    );

    create table portal.cent_departamento (
        depart_id bigint not null auto_increment,
        nome varchar(60) not null unique,
        primary key (depart_id)
    );

    create table portal.cent_membro_departamento (
        mem_depart_id bigint not null auto_increment,
        carg_id bigint,
        depart_id bigint,
        mem_id bigint,
        primary key (mem_depart_id)
    );

    create table roles (
        role_id bigint not null auto_increment,
        role_description varchar(255) unique,
        primary key (role_id)
    );

    create table user_roles (
        user_id bigint not null,
        role_id bigint not null
    );

    create table users (
        user_id bigint not null auto_increment,
        user_password varchar(255),
        user_username varchar(255) unique,
        primary key (user_id)
    );

    alter table cent_chamada 
        add index FK9718E01624A1375E (peq_id), 
        add constraint FK9718E01624A1375E 
        foreign key (peq_id) 
        references cent_pequenogrupo (peq_id);

    alter table cent_chamada 
        add index FK9718E016417EDA29 (saba_id), 
        add constraint FK9718E016417EDA29 
        foreign key (saba_id) 
        references cent_sabado (saba_id);

    alter table cent_chamada 
        add index FK9718E01688BC6E33 (mem_id), 
        add constraint FK9718E01688BC6E33 
        foreign key (mem_id) 
        references cent_membro (mem_id);

    alter table cent_grupo_cent_membro 
        add index FKD657C0F4720455A9 (membros_mem_id), 
        add constraint FKD657C0F4720455A9 
        foreign key (membros_mem_id) 
        references cent_membro (mem_id);

    alter table cent_grupo_cent_membro 
        add index FKD657C0F4202E48AC (cent_grupo_grup_id), 
        add constraint FKD657C0F4202E48AC 
        foreign key (cent_grupo_grup_id) 
        references cent_grupo (grup_id);

    alter table cent_membro 
        add index FK1E0D52A19EADD421 (past_id), 
        add constraint FK1E0D52A19EADD421 
        foreign key (past_id) 
        references cent_pastor (past_id);

    alter table cent_membro 
        add index FK1E0D52A1DC39DE7 (pro_id), 
        add constraint FK1E0D52A1DC39DE7 
        foreign key (pro_id) 
        references cent_profissao (pro_id);

    alter table cent_membro 
        add index FK1E0D52A124A1375E (peq_id), 
        add constraint FK1E0D52A124A1375E 
        foreign key (peq_id) 
        references cent_pequenogrupo (peq_id);

    alter table cent_pequenogrupo 
        add index FKD5577E53A8C14513 (coord_id), 
        add constraint FKD5577E53A8C14513 
        foreign key (coord_id) 
        references cent_membro (pro_id);

    alter table cent_pequenogrupo 
        add index FKD5577E5395BA0078 (anfi_id), 
        add constraint FKD5577E5395BA0078 
        foreign key (anfi_id) 
        references cent_membro (pro_id);

    alter table cent_questionario 
        add index FK9D677B348A35C884 (perg_id), 
        add constraint FK9D677B348A35C884 
        foreign key (perg_id) 
        references cent_pergunta (perg_id);

    alter table cent_questionario 
        add index FK9D677B3424A1375E (peq_id), 
        add constraint FK9D677B3424A1375E 
        foreign key (peq_id) 
        references cent_pequenogrupo (peq_id);

    alter table cent_questionario 
        add index FK9D677B34417EDA29 (saba_id), 
        add constraint FK9D677B34417EDA29 
        foreign key (saba_id) 
        references cent_sabado (saba_id);

    alter table cent_questionario 
        add index FK9D677B3488BC6E33 (mem_id), 
        add constraint FK9D677B3488BC6E33 
        foreign key (mem_id) 
        references cent_membro (mem_id);

    alter table portal.cent_membro_departamento 
        add index FK2CC050E2BB97E6BF (carg_id), 
        add constraint FK2CC050E2BB97E6BF 
        foreign key (carg_id) 
        references portal.cent_cargo (carg_id);

    alter table portal.cent_membro_departamento 
        add index FK2CC050E29F19808E (depart_id), 
        add constraint FK2CC050E29F19808E 
        foreign key (depart_id) 
        references portal.cent_departamento (depart_id);

    alter table portal.cent_membro_departamento 
        add index FK2CC050E288BC6E33 (mem_id), 
        add constraint FK2CC050E288BC6E33 
        foreign key (mem_id) 
        references cent_membro (mem_id);

    alter table user_roles 
        add index FK73429949EBBA393E (role_id), 
        add constraint FK73429949EBBA393E 
        foreign key (role_id) 
        references roles (role_id);

    alter table user_roles 
        add index FK7342994990E4FD1E (user_id), 
        add constraint FK7342994990E4FD1E 
        foreign key (user_id) 
        references users (user_id);
