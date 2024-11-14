# Описание
Проект для изучения процесса
создания приложения на микросервисной архитектуре
с использованием GraphQL и RabbitMQ.\
Сервис для работы со статьями.
# Требования
- [ ] Авторизация с помощью JWT-токенов (по email и паролю)
- [ ] Запуск через docker compose
- [ ] Сервисы должны общаться через RabbitMQ посредством GraphQL
- [ ] CRUD операции над статьями
# Структура
```mermaid
graph TD
    client(fa:fa-image VueJS)
    apiGateaway([fa:fa-door-open Api Gateaway])
    authenticationService{{fa:fa-microchip Authentication Service}}
    articleService{{fa:fa-microchip Article Service}}
    redis[(fa:fa-database Redis)]
    postgres[(fa:fa-database PostgreSQL)]

    client <--> apiGateaway
    apiGateaway <--> |fa:fa-code-branch RabbitMQ| authenticationService
    apiGateaway <--> |fa:fa-code-branch RabbitMQ| articleService
    authenticationService <--> redis
    authenticationService <--> postgres
    articleService <--> postgres
    authenticationService <--> |fa:fa-code-branch RabbitMQ| articleService

    subgraph **CLIENT**
    client
    end

    subgraph **SERVER**

    apiGateaway

    subgraph **DATABASE**
    redis
    postgres
    end

    subgraph **SERVISES**
    authenticationService
    articleService
    end

    end
```
# Стек технологий

<details>
<summary>
БД
</summary>

* Redis
* PostgreSQL

</details>

# Документация
# Тесты
# Запуск и развертывание
Для запуска на компьютере должен быть установлен и запущен Docker.

| Процесс    | Порт | Открыт |
| ---------- | ---- | ------ |

Первый запуск (команды выполняются в директории с `compose.yaml`)
```bat
docker compose up --build
```
Все последующие запуски
```bat
docker compose up
```
