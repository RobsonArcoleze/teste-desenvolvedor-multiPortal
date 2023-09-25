# Teste Desenvolvedor 
[![NPM](https://img.shields.io/npm/l/react)](https://github.com/devsuperior/sds1-wmazoni/blob/master/LICENSE) 

# Sobre o projeto

Neste teste consta uma aplicação full stack web mvc com java 17, spring boot 3, thymeleaf e bootstrap proposto pela empresa [Multi Portal](https://www.mportal.com.br/ "Site da Multi Portal").

#Desafio:


Para migrar uma base de dados, geramos um CSV e precisamos criar um programa para persistir os dados em banco.


Porém durante a migração alguns dados foram corrompidos, precisamos criar um programa que corrija esses dados e extraia algumas informações.


- Criar uma tela com um login e senha.
- Esses dados de login e senha não precisam ser validados com o banco, podemos apenas ter uma validação simples na tela e com dados já pre-definidos, aplicar o spring security para lidar a sessão neste caso.
- Criar uma tela que iremos carregar o CSV enviado no email do desafio, com este csv carregado iremos fazer as tarefas a seguir.
- Com o CSV carregado na tela ordene-o pelo nome.
- Conte quantos homens e mulheres existem no CSV, e exiba em tela.
- Observe que a data de nascimento está errada, ajuste para a data certa com o conforme o campo idade.
- Como um último desafio calcule a media de idade de homens e mulheres e exiba em tela .
- Agora com todos os dados corretos, grave no banco de dados e gere um novo csv para download na tela.





## Layout web
![Web 1](https://github.com/RobsonArcoleze/teste-desenvolvedor-multiPortal/blob/main/img/login.png)

![Web 2](https://github.com/RobsonArcoleze/teste-desenvolvedor-multiPortal/blob/main/img/ajuste_branco.png)

![Web 3](https://github.com/RobsonArcoleze/teste-desenvolvedor-multiPortal/blob/main/img/ajuste_populado.png)



# Tecnologias utilizadas

- Java
- Spring Boot / Data / Security
- JPA / Hibernate
- Maven
- PostgreSQL
- Flyway
- Thymeleaf
- Bootstrap
- Docker



# Como executar o projeto


### Pré-requisitos


- Java 17+
- Database: Postgres
- Nome do banco: postgres // caso rode a imagem no docker
- Senha: 72551984 // caso rode a imagem no docker
- Docker Instalado // caso rode a imagem no docker
  

### Rodando através da IDE
```bash
# clonar repositório
https://github.com/RobsonArcoleze/teste-desenvolvedor-multiPortal.git

# altere as configurações no application.yaml conforme a necessidade
server:
  port: 8025

spring:
  datasource:
    driverClassName: org.postgresql.Driver
    url: jdbc:postgresql://localhost:5432/postgres
    username: postgres
    password: 72551984
  jpa:
    database: POSTGRESQL
    hibernate.ddl-auto: none
    show-sql: false

  flyway:
    baseline-on-migrate: true

# executar o projeto
execute através da IDE de sua preferência
```


### Rodando através da imagem docker
```bash
# Puxe a imagem docker para a sua maquina
docker pull robsonarcoleze/testedesenvolvedor

# Execute a imagem
docker run -p 8025:8025 robsonarcoleze/testedesenvolvedor

```

# Ponto importante


Na hora de expor o link do novo CSV para download, não consegui colocar o arquivo dentro da pasta static para ficar visivel para o navegador.
Então ao clicar no link e fazer o download ocorre um erro.


### Solução para baixar o arquivo

Em Download: Está o caminho do arquivo, será necessário selecionar esse caminho, copiar e colar em uma nova aba do navegador, o download iniciara e o arquivo csv será salvo em sua maquina.


#### Lembre-se que codifiquei toda a logica baseada no arquivo enviado pelo recrutador, para realizar os testes, deverá ser usado o mesmo arquivo!


![Web 3](https://github.com/RobsonArcoleze/teste-desenvolvedor-multiPortal/blob/main/img/ajuste_populado.png)

# Autor

Robson de Oliveira Arcoleze

https://www.linkedin.com/in/robsonarcoleze/
