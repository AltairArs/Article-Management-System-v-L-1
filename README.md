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
    client(Vue JS)
    apiGateaway([Api Gateaway])
    authenticationService{{Authentication Service}}
    articleService{{Article Service}}
    redis[(Redis)]
    postgres[(PostgreSQL)]

    client <--> apiGateaway
    apiGateaway <--> |RabbitMQ| authenticationService
    apiGateaway <--> |RabbitMQ| articleService
    authenticationService <--> redis
    authenticationService <--> postgres
    articleService <--> postgres

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
