# nutrisoft-project
:herb: Um projeto para registro de serviços de nutrição desenvolvido em Java com persistência de dados MySQL.

## Interfaces 💻

### Home
<p align="center">
    <img src="/img/home.png">
</p>

### Autenticação
<p align="center">
    <img src="/img/auth.png">
</p>

### Menu de operações
<p align="center">
    <img src="/img/menu.png">
</p>

### Cadastro
<p align="center">
    <img src="/img/register-service.png">
</p>

### Listagem
<p align="center">
    <img src="/img/service-list.png">
</p>

## Pré-requisitos 📋

Para executar esta aplicação em seu dispositivo você precisará ter instalado e configurado:
* <a href="https://www.mysql.com/" target="_blank">Java 8</a>
* <a href="https://www.oracle.com/java/technologies/javase-jdk8-downloads.html" target="_blank">MySQL 5</a>

## Script para criação do banco de dados 💾

```
CREATE DATABASE nutrisoft;

USE nutrisoft;

CREATE TABLE usuariotb (
  id int NOT NULL AUTO_INCREMENT,
  email varchar(255) DEFAULT NULL,
  senha varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE  servicotb (
  id int NOT NULL AUTO_INCREMENT,
  nome varchar(255) DEFAULT NULL,
  descricao varchar(255) DEFAULT NULL,
  data timestamp NULL DEFAULT NULL,
  valor decimal(6,2) DEFAULT NULL,
  id_usuario INTEGER,
  FOREIGN KEY (id_usuario) REFERENCES usuariotb(id),
  PRIMARY KEY (id)
);
```

## Licença 📄

[MIT](/LICENSE) &copy; Carlos Henrique da Costa Silva

<p align="center" style="margin-top: 20px; border-top: 1px solid #eee; padding-top: 20px;">Feito com :brown_heart: por <strong> Carlos Henrique da Costa Silva </strong> </p>
