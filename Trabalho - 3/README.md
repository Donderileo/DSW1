# Sistema de Agendamento Donaderi

O sistema de agendamento proposto, tem seus requisitos disponíveis em: 

[Requisitos do Sistema](https://github.com/Donderileo/DSW1/blob/main/SistemaAgendamento/Requisitos-C1.pdf)


----

Para executar este projeto Maven, alguns passos devem ser executados.

----


## MySQL

Também, precisamos de um servidor MySql ativo, que pode ser executado com

```bash
mysql -uroot -p
```

Com o servidor MySql ativo, é necessário alterar os valores de password e senha no arquivo application.properties, situado em:
    
   Trabalho-2/src/main/resources/templates/application.properties

Atualizando as variáveis spring.datasource.username e spring.datasource.password com seu username e password iguais aos usados no MySql.

Com tudo configurado, precisamos inserir o Database no mySQL

```bash
create database SistemaAgendamento
```

Para executar o Spring, basta em um terminal executar:
```bash
mvn spring-boot:run
```

Acesse o site pelo link [localhost:8080/](localhost:8080/)

----

## Funcionalidades  

CRUD para Clientes, Profissionais e Consultas  

**C - CREATE**  
POST http://localhost:8080/clientes  
POST http://localhost:8080/profissionais  

**R - READ**  

GET http://localhost:8080/clientes  
GET http://localhost:8080/clientes/{id}  

GET http://localhost:8080/profissionais  
GET http://localhost:8080/profissionais/{id}  

GET http://localhost:8080/consultas  
GET http://localhost:8080/consultas/{id}  
GET http://localhost:8080/consultas/clientes/{id}  
GET http://localhost:8080/consultas/profissionais/{id}  

**U - UPDATE**  

PUT http://localhost:8080/clientes/{id}  
PUT http://localhost:8080/profissionais/{id}  

**D - DELETE**  

DELETE http://localhost:8080/clientes/{id}  
DELETE http://localhost:8080/profissionais/{id  

----

## Checklist 
Obs.: Além do que foi implementado no Trabalho 2, nesta etapa também fora implementado: 

| Requisitos | Status |
| ------------- | ------------- |
| R1: CRUD de Clientes REST | Implementado |
| R2: CRUD de Profissional REST | Implementado |
| R2: GET de Consultas REST | Implementado |


Este projeto é um trabalho acadêmico da disciplina de Desenvolvimento de Software para Web ministrada pelo Prof. Dr. Delano Medeiros Beder.

O projeto foi desenvolvido inteiramente pelos dois membros em conjunto.

* [Leonardo Donderi Rodrigues](https://github.com/Donderileo) 
* [Vitor Donadelli Rodrigues](https://github.com/VitorDonadelli)

