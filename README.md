# Проект "Taxi Company"

Этот проект представляет собой систему управления такси, реализованную с использованием Java и фреймворка Spring Boot. Он предоставляет API для администрирования, управления автомобилями, пользовательскими аккаунтами и арендой машин.

## Технологии

- **Java 17**
- **Spring Boot 2.7.12**
- **Spring Security**
- **PostgreSQL 15.4**
- **Swagger OpenAPI**
- **JWT (JSON Web Tokens)**
- **Swagger OpenAPI**
- **Docker**

## Описание проекта

Проект построен на Java с использованием Spring Boot и предоставляет возможности управления системой такси через API. Это включает создание, удаление и управление автомобилями, аренду автомобилей для пользователей, управление ролями пользователей и аутентификацию с помощью JWT-токенов.
### Swagger OpenAPI

В проекте использован Swagger OpenAPI для документирования и визуализации API. После запуска проекта, вы можете посетить `/swagger-ui.html`, чтобы просмотреть и протестировать эндпоинты API в интерактивной документации Swagger.

## Запуск проекта

Для запуска проекта необходимо установить Docker и Docker Compose. После установки выполните следующие шаги:

1. Клонируйте репозиторий.
2. Перейдите в корневую директорию проекта.
3. Запустите приложение с помощью команды `docker-compose up --build`.

После успешного запуска, приложение будет доступно по адресу `http://localhost:8080`.
