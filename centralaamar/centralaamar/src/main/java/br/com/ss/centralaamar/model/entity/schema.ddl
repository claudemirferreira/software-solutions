
    create table centralaamar.chamada (
        cham_id integer not null auto_increment unique,
        presente bit,
        mem_id integer,
        peq_id integer,
        saba_id integer,
        primary key (cham_id)
    );

    create table centralaamar.grupo (
        grup_id integer not null auto_increment unique,
        primary key (grup_id)
    );

    create table centralaamar.membro (
        mem_id integer not null auto_increment unique,
        amigoContato varchar(255),
        bairro varchar(255),
        batizado varchar(255),
        celular varchar(255),
        dataBatismo datetime,
        dataNascimento datetime,
        email varchar(255),
        endereco varchar(255),
        foneComercial varchar(255),
        foneResidencial varchar(255),
        interesse varchar(30),
        mae varchar(255),
        membroIgreja varchar(255),
        membrosBatizadoFamilia integer not null,
        modoConversao varchar(255),
        nome varchar(255),
        pai varchar(255),
        sexo varchar(255),
        temFilho varchar(255),
        past_id integer,
        peq_id integer,
        pro_id integer,
        primary key (mem_id)
    );

    create table centralaamar.pastor (
        past_id integer not null auto_increment unique,
        nome varchar(100) not null unique,
        primary key (past_id)
    );

    create table centralaamar.pequenogrupo (
        peq_id integer not null auto_increment unique,
        descricao varchar(100) not null,
        primary key (peq_id)
    );

    create table centralaamar.pergunta (
        perg_id integer not null auto_increment unique,
        descricao varchar(255),
        primary key (perg_id)
    );

    create table centralaamar.profissao (
        pro_id integer not null auto_increment unique,
        descricao varchar(255),
        primary key (pro_id)
    );

    create table centralaamar.questionario (
        quest_id integer not null auto_increment unique,
        descricao varchar(255),
        mem_id integer,
        peq_id integer,
        saba_id integer,
        primary key (quest_id)
    );

    create table centralaamar.sabado (
        saba_id integer not null auto_increment unique,
        data datetime not null unique,
        primary key (saba_id)
    );

    create table grupo_membro (
        grupo_grup_id integer not null,
        membros_mem_id integer not null,
        unique (membros_mem_id)
    );

    alter table centralaamar.Eleitor 
        add index FK1A1DCC68ACB96 (res_id), 
        add constraint FK1A1DCC68ACB96 
        foreign key (res_id) 
        references centralaamar.Responsavel (res_id);

    alter table centralaamar.Eleitor 
        add index FK1A1DCC59AD2B21 (loc_id), 
        add constraint FK1A1DCC59AD2B21 
        foreign key (loc_id) 
        references centralaamar.Local (loc_id);

    alter table centralaamar.chamada 
        add index FK2C0AD7AD24A1375E (peq_id), 
        add constraint FK2C0AD7AD24A1375E 
        foreign key (peq_id) 
        references centralaamar.pequenogrupo (peq_id);

    alter table centralaamar.chamada 
        add index FK2C0AD7AD417EDA29 (saba_id), 
        add constraint FK2C0AD7AD417EDA29 
        foreign key (saba_id) 
        references centralaamar.sabado (saba_id);

    alter table centralaamar.chamada 
        add index FK2C0AD7AD88BC6E33 (mem_id), 
        add constraint FK2C0AD7AD88BC6E33 
        foreign key (mem_id) 
        references centralaamar.membro (mem_id);

    alter table centralaamar.membro 
        add index FKBFC28C2A9EADD421 (past_id), 
        add constraint FKBFC28C2A9EADD421 
        foreign key (past_id) 
        references centralaamar.pastor (past_id);

    alter table centralaamar.membro 
        add index FKBFC28C2ADC39DE7 (pro_id), 
        add constraint FKBFC28C2ADC39DE7 
        foreign key (pro_id) 
        references centralaamar.profissao (pro_id);

    alter table centralaamar.membro 
        add index FKBFC28C2A24A1375E (peq_id), 
        add constraint FKBFC28C2A24A1375E 
        foreign key (peq_id) 
        references centralaamar.pequenogrupo (peq_id);

    alter table centralaamar.questionario 
        add index FKF5C3A9FD24A1375E (peq_id), 
        add constraint FKF5C3A9FD24A1375E 
        foreign key (peq_id) 
        references centralaamar.pequenogrupo (peq_id);

    alter table centralaamar.questionario 
        add index FKF5C3A9FD417EDA29 (saba_id), 
        add constraint FKF5C3A9FD417EDA29 
        foreign key (saba_id) 
        references centralaamar.sabado (saba_id);

    alter table centralaamar.questionario 
        add index FKF5C3A9FD88BC6E33 (mem_id), 
        add constraint FKF5C3A9FD88BC6E33 
        foreign key (mem_id) 
        references centralaamar.membro (mem_id);

    alter table grupo_membro 
        add index FK55A764C0720455A9 (membros_mem_id), 
        add constraint FK55A764C0720455A9 
        foreign key (membros_mem_id) 
        references centralaamar.membro (mem_id);

    alter table grupo_membro 
        add index FK55A764C0D357F303 (grupo_grup_id), 
        add constraint FK55A764C0D357F303 
        foreign key (grupo_grup_id) 
        references centralaamar.grupo (grup_id);
