# 📃Описание
Проект для изучения процесса
создания приложения на микросервисной архитектуре
с использованием GraphQL и RabbitMQ.\
Сервис для работы со статьями.
## 🔥Основные моменты
* 🔶Spring
* 🔶GraphQL
  * 🔶Validation
  * 🔶Custom Types
    * 🔶Local Date Time
* 🔶RabbitMQ
* 🔶Microservices
* 🔶JWT-Authentication
# 📗Требования
- [ ] Аутентификация с помощью JWT-токенов (по email и паролю)
- [x] Запуск через docker compose
- [ ] Сервисы должны "общаться" через RabbitMQ
- [x] CRUD операции над статьями
- [x] В качестве протокола взаимодействия должен использоваться GraphQL
# 🎨Структура
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
# 🔧Стек технологий

<details>
<summary>
<big>БД</big>
</summary>

* Redis
* PostgreSQL

</details>

# 📚Документация
# 📈Тесты

<details>
<summary>
<big>Article Service</big>
</summary>

### Получение всех статей
Запрос
```graphql
query getArticles{
  getAllArticles {
      id,
      title,
      content
  }
}
```
Ответ
```json
{
    "data": {
        "getAllArticles": [
            {
                "id": "2",
                "title": "some title",
                "content": "some content"
            },
            {
                "id": "3",
                "title": "some title",
                "content": "some content"
            }
        ]
    }
}
```
### Получение отдельной статьи
Запрос
```graphql
query getArticle{
    getArticle(id: 2){
        title,
        content
    }
}
```
Ответ
```json
{
    "data": {
        "getArticle": {
            "title": "some title",
            "content": "some content"
        }
    }
}
```
### Создание статьи
Запрос
```graphql
mutation createArticle{
    createArticle(title: "some title", content: "some content"){
        id,
        title,
        content
    }
}
```
Ответ
```json
{
    "data": {
        "createArticle": {
            "id": "4",
            "title": "some title",
            "content": "some content"
        }
    }
}
```
### Изменение статьи
Запрос
```graphql
mutation updateArticle{
    updateArticle(id: 4, title: "new_title"){
        title,
        content
    }
}
```
Ответ
```json
{
    "data": {
        "updateArticle": {
            "title": "new_title",
            "content": "some content"
        }
    }
}
```
### Удаление статьи
Запрос
```graphql
mutation deleteArticle{
    deleteArticle(id: 4)
}
```
Ответ
```json
{
    "data": {
        "deleteArticle": null
    }
}
```

</details>

# 🚩Запуск и развертывание
Для запуска на компьютере должен быть установлен и запущен Docker.

|         Процесс         |  Порт  | Открыт*  |
|:-----------------------:|:------:|:--------:|
|     Article Service     |  8081  |   Нет    |
| Authentication Service  |  8082  |   Нет    |
|       PostgreSQL        |  5432  |   Нет    |
|          Redis          |  6379  |   Нет    |
|         PgAdmin         | 15432  |    Да    |
|      Api Gateaway       |  8080  |    Да    |

> *для внешнего клиента

Первый запуск (команды выполняются в директории с `compose.yaml`)
```bat
docker compose up --build
```
Все последующие запуски
```bat
docker compose up
```
