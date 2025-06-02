# CustomerConnect

## Sistema de Gerenciamento de Clientes

Bem-vindo ao **CustomerConnect**, um projeto envolvente e prático projetado para ajudá-lo a dominar os fundamentos do Spring Boot e do Spring Data JPA. Neste projeto, você criará um robusto Sistema de Gerenciamento de Clientes que executa operações CRUD em uma entidade Cliente.

Ao final deste módulo, você terá uma compreensão sólida de como conectar a um banco de dados usando Spring Data JPA e de como implementar operações CRUD em uma aplicação Spring Boot.

---

## Regras de Negócio

### Dados Cadastrais

O sistema gerencia as principais informações dos clientes:
- **Nome Completo**
- **CPF**
- **Email**
- **Telefone Celular**
- **Data de registro e atualização** do cliente no sistema (para fins de auditoria)

### Cadastro Único

- Não é permitido cadastrar clientes com **id**, **cpf** ou **email** repetidos.

### Busca Flexível e Personalizada

- Permite **paginação** e **ordenação** dos resultados.
- Permite busca por **CPF** e/ou **Email**.

---

## Endpoints REST

### Criar Cliente

- **POST `/customers`**
  - Parâmetros requeridos:
    - `fullName`: Nome completo
    - `cpf`: CPF do cliente
    - `email`: Email do cliente
    - `phoneNumber`: Telefone celular
  - **Retorna**: `customerId` (Identificador do cliente no sistema)

### Consultar Clientes

- **GET `/customers`**
  - Parâmetros de consulta:
    - `page`: Número da página
    - `pageSize`: Quantidade de itens por página
    - `orderBy`: Ordenação pela data de criação
    - `email`: Busca por email
    - `cpf`: Busca por CPF
  - **Retorna**: Dados dos clientes conforme a paginação

### Atualizar Cliente

- **PUT `/customers/{customerId}`**
  - Parâmetros requeridos:
    - `fullName`: Nome completo
    - `email`: Email do cliente
    - `phoneNumber`: Telefone celular

### Deletar Cliente

- **DELETE `/customers/{customerId}`**  
  Remove o cliente do cadastro.

---

## Tecnologias Utilizadas

- Java
- Spring Boot
- Spring Data JPA
- Maven
- Banco de Dados Relacional (H2, PostgreSQL, ou outro configurável)

---

## Como Executar o Projeto

1. Clone o repositório:
   ```bash
   git clone https://github.com/matalvesdev/CustomerConnectWithSpringDataJPA.git
   ```

2. Acesse o diretório do projeto:
   ```bash
   cd CustomerConnectWithSpringDataJPA
   ```

3. Execute o projeto com o Maven ou sua IDE favorita:
   ```bash
   ./mvnw spring-boot:run
   ```

4. Acesse a API pelos endpoints definidos acima.

---

## O que aprendi:

- Configuração de um projeto Spring Boot
- Configuração e conexão com banco de dados relacional
- Definição de entidades JPA
- Criação de repositórios com Spring Data JPA
- Implementação de camada de serviço
- Exposição de APIs RESTful para operações CRUD

---

Este projeto irá te capacitar com o conhecimento e as habilidades necessárias para construir e gerenciar sistemas backend robustos usando Spring Boot e Spring Data JPA.

---
