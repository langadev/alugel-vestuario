# Projeto de Aluguel de Vestuário

Este projeto é uma plataforma web para aluguel de roupas, onde usuários podem visualizar, reservar e gerenciar o aluguel de vestuário online.

---

## Índice

- [Descrição](#descrição)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Funcionalidades](#funcionalidades)
- [Pré-requisitos](#pré-requisitos)
- [Instalação](#instalação)
- [Como Usar](#como-usar)
- [Contribuição](#contribuição)
- [Licença](#licença)
- [Contato](#contato)

---

## Descrição

Este sistema permite que usuários se cadastrem, naveguem pelo catálogo de roupas disponíveis para aluguel, façam reservas e acompanhem seus pedidos. Administradores podem adicionar, editar ou remover peças de roupa, além de gerenciar usuários e aluguéis.

---

## Tecnologias Utilizadas

- **Backend:** Spring Boot  
- **Banco de Dados:** MySQL  
- **Autenticação:** Basic Auth  
- **Outras:** Documentação com Swagger

---

## Funcionalidades

- Cadastro e login de usuários
- Visualização do catálogo de roupas
- Pesquisa por categoria e tamanho
- Reserva e agendamento de itens
- Aluguel de vestuário
- Gerenciamento de reservas
- Gerenciamento de usuários
- Gerenciamento do catálogo de itens

---

## Pré-requisitos

- JDK instalado (Java 17 ou superior recomendado)
- MySQL instalado e configurado
- Git (opcional, para clonar o repositório)
- IDE como IntelliJ ou VS Code (opcional)

---
## Como Usar

1. Inicie o projeto (backend).
2. Acesse a documentação da API via Swagger no navegador:
   -url:http://localhost:8080/swagger-ui/index.html
3. No Swagger, você pode:
- Visualizar todos os endpoints disponíveis
- Testar requisições (GET, POST, PUT, DELETE)
- Ver exemplos de DTOs e respostas da API

4. Testando a aplicação:
- Cadastre um usuário usando o endpoint `/register`
- Ao preencher o cadastro, selecione o campo de perfil com um dos seguintes valores:
  - `ADMIN` (para ter acesso a rotas administrativas)
  - `CLIENTE`
  - `FUNCIONARIO`
  - `USER`
- Após o cadastro, use as credenciais para autenticar via **Basic Auth** diretamente no Swagger
- Explore os endpoints para navegar pelo catálogo, fazer reservas e acompanhar os aluguéis

---

## Instalação

1. Clone este repositório:

   ```bash
   git clone https://github.com/seu-usuario/aluguel-vestuario.git
