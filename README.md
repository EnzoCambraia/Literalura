# Literalura

Este projeto Java é uma aplicação de console que interage com a API [Gutendex](https://gutendex.com/) para buscar informações sobre livros e autores, e persiste esses dados em um banco de dados PostgreSQL.

## Funcionalidades

- Buscar livros pelo título na API Gutendex.
- Listar todos os livros registrados no banco de dados.
- Listar todos os autores registrados no banco de dados.
- Listar autores vivos em um determinado ano.
- Listar livros em um determinado idioma.

## Tecnologias Utilizadas

- Java 21
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Maven

## Como Rodar o Projeto

### Pré-requisitos

Certifique-se de ter as seguintes ferramentas instaladas em sua máquina:

- Java Development Kit (JDK) 21 ou superior
- Apache Maven
- PostgreSQL

### Configuração do Banco de Dados

1.  Crie um banco de dados PostgreSQL para o projeto (ex: `literalura_db`).
2.  Atualize o arquivo `src/main/resources/application.properties` com as credenciais do seu banco de dados:

    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/literalura_db
    spring.datasource.username=seu_usuario
    spring.datasource.password=sua_senha
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    spring.jpa.properties.hibernate.format_sql=true
    ```

### Executando a Aplicação

1.  Navegue até o diretório raiz do projeto no terminal (onde o `pom.xml` está localizado).
2.  Compile o projeto usando Maven:

    ```bash
    mvn clean install
    ```

3.  Execute a aplicação Spring Boot:

    ```bash
    mvn spring-boot:run
    ```

4.  A aplicação será iniciada e você poderá interagir com o menu no console.

## Estrutura do Projeto

- `com.alura.literalura.model`: Contém as classes de entidade (`Livro`, `Autor`) e os records para mapeamento da API (`DadosLivro`, `DadosAutor`, `DadosPesquisa`).
- `com.alura.literalura.repository`: Interfaces de repositório para acesso ao banco de dados usando Spring Data JPA.
- `com.alura.literalura.service`: Classes de serviço que contêm a lógica de negócio e a comunicação com a API externa.
- `com.alura.literalura.principal`: Contém a classe `Principal`, que é a interface de linha de comando da aplicação.
- `LiteraluraApplication.java`: Classe principal da aplicação Spring Boot.

---

Desenvolvido como parte do desafio Literalura ONE da Alura.

