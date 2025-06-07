# My Tasks

O projeto my-tasks foi feito com o objetivo de praticar o desenvolvimento utilizando as ferramentas Angular, Spring Boot e docker. Se trata de um app web voltado para criação de tarefas, com sistema de filtros e home page personalizada ao usuário.

## Ferramentas utilizadas
- MySQL (Database)
    - Criação de um banco
    - Criação e visualização de tabelas
    - phpMyAdmin: interface para o banco de dados
- Spring Boot (Backend)
    - Spring Web
    - Spring Data JPA: conexão com o banco de dados
    - Spring Security: autenticação e proteção de rotas
    - Utilização de token JWT para autenticação
- Angular (Frontend)
    - SPA (Single Page Aplication)
    - Routes
    - Chamadas de requisições
    - Cookies
    - Tailwind CSS
- Docker
    - Dockerfile
    - Docker compose
## Como rodar o projeto?

Para rodar o projeto basta ter o docker instalado na sua máquina e rodar o seguinte comando:

```bash
docker-compose up -d
```

Pronto! O projeto estará disponível em http://localhost:4200. Além disso, um container do phpmyadmin estará rodando em http://localhost:8081, possibilitando a visualização do banco de dados e suas tabelas.
