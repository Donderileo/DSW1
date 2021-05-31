create database SistemaAgendamento;
use SistemaAgendamento;

create table Cliente(
    cpf varchar(16) not null unique,
    nome varchar(256) not null,
    email varchar(256) not null unique,
    senha varchar(256) not null,
    telefone varchar(13) not null,
    sexo varchar(256) not null,
    dataNasc date not null,

    primary key (cpf)
);

create table Profissional(
    cpf varchar(16) not null unique,
    nome varchar(256) not null,
    email varchar(256) not null unique,
    senha varchar(256) not null,
    especialidade varchar(256) not null,
	curriculo varchar(256) not null,
    
	primary key (cpf)
);

create table Consulta(
	cpfCliente varchar(11) not null,
	cpfProfissional varchar(14) not null,
	data datetime not null,
    
	foreign key (cpfCliente) references Cliente(cpf),
    	foreign key (cpfProfissional) references Profissional(cpf),
 	primary key (cpfCliente, cpfProfissional, data)
);

create table Admin(
    nome varchar(256) not null unique,
    email varchar(256) not null unique,
    senha varchar(256) not null,
    papel varchar(256) not null,
    
	primary key (nome)
);

insert into Cliente(cpf, nome, email, senha, telefone, sexo, dataNasc) values ('1', 'Fulano', 'fulano@gmail.com', 'fulano', '19 1111-1111', 'Masculino', '2001/01/01');

insert into Cliente(cpf, nome, email, senha, telefone, sexo, dataNasc) values ('2', 'Cicrano', 'cicrano@gmail.com', 'cicrano', '19 2222-2222', 'Masculino', '2002/02/20');

insert into Profissional(cpf, nome, email, senha, especialidade, curriculo) values ('3', 'Beltrano', 'beltrano@gmail.com', 'beltrano', 'Dentista', 'linkedin');

insert into Admin(nome, email, senha, papel) values ('admin', 'admin', 'admin', 'admin');
