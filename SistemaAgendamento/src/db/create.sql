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
