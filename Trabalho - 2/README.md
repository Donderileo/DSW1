# Sistema de Agendamento Donaderi

O sistema de agendamento proposto, tem seus requisitos disponíveis em: 

----

Para executar este projeto Spring, alguns passos devem ser executados.

----


## MySQL

Também, precisamos de um servidor MySql ativo, que pode ser executado com

```bash
mysql -uroot -p
```

Com o servidor MySql ativo, é necessário alterar os valores de password e senha no arquivo GenericDao.java, situado em:
    
    Trabalho-2/src/main/resources/templates/application.properties

Atualizando as variáveis spring.datasource.username e spring.datasource.password com seu username e password iguais aos usados no MySql.

Com tudo configurado, precisamos inserir o Database no mySQL

    create database SistemaAgendamento


Para executar o Spring, basta em um terminal executar:

    mvn spring-boot:run


Acesse o site pelo link [localhost:8080/](localhost:8080/)

----

## Checklist 

| Requisitos | Status |
| ------------- | ------------- |
| R1: CRUD de Clientes | Implementado |
| R2: CRUD de Profissional | Implementado |
| R3: Listagem de Profissionais| Implementado |
| R4: Agendamento de Consultas + ~~Email~~ | Parcialmente Implementado |
| R5: Validação de agendamentos | Implementado |
| R6: Listagem de Consultas de um Cliente | Implementado |
| R7: Listagem de Consultas de um Profissional| Implementado |
| R8: Internacionalização em dois idiomas | Implementado |
| R9: Validação de tamanho/formato | Implementado |


Este projeto é um trabalho acadêmico da disciplina de Desenvolvimento de Software para Web ministrada pelo Prof. Dr. Delano Medeiros Beder.

O projeto foi desenvolvido inteiramente pelos dois membros em conjunto.

* [Leonardo Donderi Rodrigues](https://github.com/Donderileo) 
* [Vitor Donadelli Rodrigues](https://github.com/VitorDonadelli)


