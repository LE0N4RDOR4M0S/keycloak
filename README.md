# API de Gestão de Produtos com Keycloak (Backend)

Esta é uma API RESTful construída com Spring Boot para gerenciar produtos. A aplicação é protegida utilizando Keycloak como provedor de identidade, implementando autenticação e autorização baseada em `Roles` (RBAC) com OAuth 2.0 e JWT.

## Funcionalidades

-   Endpoints CRUD para gerenciamento de produtos.
-   Integração completa com Keycloak para autenticação e autorização.
-   Controle de acesso baseado em `Roles` (`USER` e `ADMIN`).
-   Validação de tokens JWT em cada requisição.
-   Banco de dados em memória (H2) para facilitar o desenvolvimento e testes.

## Tecnologias Utilizadas

-   **Java 17**
-   **Spring Boot 3.x**
-   **Spring Security 6.x** (OAuth2 Resource Server)
-   **Maven** (Gerenciador de dependências)
-   **JPA / Hibernate** (Persistência de dados)
-   **H2 Database** (Banco de dados em memória)
-   **Keycloak** (Provedor de Identidade e Acesso)
-   **Docker & Docker Compose**

## Pré-requisitos

Antes de começar, certifique-se de que você tem as seguintes ferramentas instaladas:
-   JDK 17 ou superior
-   Apache Maven 3.8+
-   Docker e Docker Compose

##  Configuração e Execução

### 1. Iniciar o Keycloak

A forma mais simples de iniciar o Keycloak é utilizando o `docker-compose.yml` fornecido na raiz do projeto.

```bash
# Inicia o container do Keycloak em segundo plano
docker-compose up -d
```
O Keycloak estará disponível em `http://localhost:8081`.
-   **Usuário Admin:** `admin`
-   **Senha Admin:** `admin`

Você precisará configurar o Keycloak conforme as instruções do artigo (criar o realm `realm-keycloak`, o client `keycloak-service`, as roles `user` e `admin`, e os usuários de teste).

### 2. Configurar a Aplicação

O arquivo de configuração principal é o `src/main/resources/application.properties`. A propriedade mais importante é a URL do emissor do token:

```properties
# URL do realm que emitirá os tokens.
spring.security.oauth2.resourceserver.jwt.issuer-uri=http://localhost:8081/realms/realm-keycloak
```

### 3. Executar a API

Utilize o Maven para compilar e executar a aplicação:

```bash
# Executa a aplicação Spring Boot
mvn spring-boot:run
```
A API estará disponível em `http://localhost:8080`.

## Endpoints da API

| Método | Endpoint         | Descrição                  | Role Necessária |
| :----- | :--------------- | :------------------------- | :-------------- |
| `GET`  | `/api/products`  | Lista todos os produtos.   | `USER` ou `ADMIN` |
| `POST` | `/api/products`  | Cria um novo produto.      | `ADMIN`         |

```bash
mvn test
