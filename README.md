# nutrisoft-project
:herb: Um projeto para registro de serviços de nutrição desenvolvido em Java com persistência de dados MySQL.

## :computer: Interfaces

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

## :rocket: Tecnologias utilizadas  
O projeto foi feito utilizando as seguintes tecnologias:

- [Java](https://www.java.com/pt_BR/download/faq/java8.xml)
- [Java Swing](https://docs.oracle.com/javase/tutorial/uiswing/)
- [MySQL](https://dev.mysql.com/)
{...}

## :clipboard: Pré-requisitos

Para executar esta aplicação em seu dispositivo você precisará ter instalado e configurado:
* <a href="https://www.mysql.com/" target="_blank">Java 8</a>
* <a href="https://www.oracle.com/java/technologies/javase-jdk8-downloads.html" target="_blank">MySQL 5</a>

## :floppy_disk: Script para criação do banco de dados

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

## :fire: Instalação e execução
Execute a classe [Executavel](/src/br/com/nutrisoft/executavel/Executavel.java) localizada em `src/br/com/nutrisoft/executavel/Executavel.java`

## :page_facing_up: Licença 
Este projeto é desenvolvido sob a licença MIT. Veja o arquivo [LICENSE](LICENSE.md) para saber mais detalhes.

<p align="center" style="margin-top: 20px; border-top: 1px solid #eee; padding-top: 20px;">Feito com :brown_heart: por <strong> Carlos Henrique da Costa Silva </strong> </p>
