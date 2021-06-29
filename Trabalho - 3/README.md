# Sistema de Agendamento Donaderi

O sistema de agendamento proposto, tem seus requisitos disponíveis em: 

[Requisitos do Sistema](https://github.com/Donderileo/DSW1/blob/main/SistemaAgendamento/Requisitos-C1.pdf)


----

Para executar este projeto Maven, alguns passos devem ser executados.

----


## Tomcat 

É necessário possuir o TomCat instalado e ativo, para isso deve-se seguir este roteiro: [https://github.com/delanobeder/DSW1/blob/master/Modulo02/Roteiro02-01.md](https://github.com/delanobeder/DSW1/blob/master/Modulo02/Roteiro02-01.md)

----

## MySQL

Também, precisamos de um servidor MySql ativo, que pode ser executado com

```bash
mysql -uroot -p
```

Com o servidor MySql ativo, é necessário alterar os valores de password e senha no arquivo GenericDao.java, situado em:
    
    SistemaAgendamento/src/main/java/br/ufscar/dc/dsw/dao/ConsultaDAO.java

Atualizando a função getConnection com seu login e senha iguais aos usados no MySql.

```java
protected Connection getConnection() throws SQLException {
    
    String url = "jdbc:mysql://localhost:3306/SistemaAgendamento";
    
    return DriverManager.getConnection(url, "root", "password");
}
```

Com tudo configurado, precisamos inserir o Database e as Tabelas necessárias no banco de dados, para isso execute o arquivo `create.sql` no terminal mysql

```bash
source <PathAteOSistemaAgendamento>/SistemaAgendamento/src/db/create.sql
```

O script já criará alguns registros de cliente, um profissional, consultas e administrador.


Acesse o site pelo link [localhost:8080/SistemaAgendamento](localhost:8080/SistemaAgendamento)

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

