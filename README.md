# Разработка автоматизированной информационной системы управления учебно-тренировочными процессами в спортивных комплексах с функцией мониторинга результативности обучения

---

## Предметная область

Современные учебно-тренировочные процессы всё чаще переходят в цифровую среду, поскольку это позволяет сделать обучение 
более удобным, прозрачным и эффективным. Пользователям важно не просто получать информацию, а иметь возможность активно 
взаимодействовать с системой и контролировать свой прогресс.

В частности, для обучающихся и инструкторов значимы такие возможности, как:
- отслеживание прогресса обучения и результатов тренировок
- получение персонализированных рекомендаций с учётом уровня подготовки
- удобное взаимодействие с тренером и обратная связь

На практике многие существующие решения решают лишь отдельные задачи: одни ориентированы на хранение данных, другие — 
на коммуникацию или планирование. Это приводит к разрозненности информации и усложняет работу пользователей.
Разрабатываемая система направлена на объединение всех ключевых процессов в единую платформу, которая позволит 
централизованно управлять данными, упростить взаимодействие между участниками и повысить эффективность процесса.

---

## Стек технологий

- Java 17
- Spring Boot
- PostgreSQL

---

## Как запустить проект

1. Клонировать репозиторий 
   - `git clone https://github.com/edlwss/sporta.git`
2. Запустить приложение
   - `mvn spring-boot:run` или через IntelliJ IDEA (Run Application)

## Основные маршруты API

### Аутентификация

- `POST /sporta/api/auth`  
  Авторизация пользователя. Возвращает JWT-токен.

### Студенты

- `POST /sporta/api/students/registration`  
  Регистрация нового студента в системе.

- `GET /sporta/api/students`  
  Получение списка всех студентов.

- `GET /sporta/api/student/{id}`  
  Получение информации о студенте по идентификатору.

- `PATCH /sporta/api/student/{id}/edit`  
  Обновление данных студента.

- `DELETE /sporta/api/student/{id}/delete`  
  Удаление студента (вместе с привязанным пользователем

---
## Примеры запросов и ответов

### Регистрация студента

**Запрос:**

```json
{
  "login": "ivan",
  "password": "123456",
  "email": "ivan@example.com",
  "phone": "79304100585",
  "fullNameLastName": "Ivan",
  "fullNameFirstName": "Ivanovich",
  "fullNameMiddleName": "Ivanov",
  "gender": "Male",
  "birthDate": "2006-03-22"
}
```

**Ответ:**

```json
{
  "id": 4,
  "user": {
    "id": 4,
    "login": "ivan",
    "password": "$2a$10$9j6SG6b0kOtOxkZ6/Qmg6e6NdLG2ASXedZJswc3ZjM.I.ao3orfnK",
    "email": "ivan@example.com",
    "phone": "79304100585",
    "registrationDate": "2026-03-22T18:43:09.0969184",
    "role": {
      "id": 1,
      "name": "student"
    }
  },
  "fullName": {
    "lastName": "Ivan",
    "firstName": "Ivanovich",
    "middleName": "Ivanov",
    "fullName": "Ivan Ivanovich Ivanov"
  },
  "gender": "Male",
  "dateOfBirth": "2006-03-22",
  "photo": null
}
```

### Ошибка валидации (422 Unprocessable Entity)

Возникает при отсутствии обязательных полей или некорректных значениях.

**Запрос:**

```json
{
  "login": "ivan",
  "email": "ivan@example.com",
  "phone": "79304100585",
  "fullNameLastName": "Ivan",
  "fullNameFirstName": "Ivanovich",
  "fullNameMiddleName": "Ivanov",
  "gender": "Male",
  "birthDate": "2006-03-22"
}
```

**Ответ:**

```json
{
  "status": "Unprocessable Entity",
  "message": "Request validation failed",
  "timestamp": "2026-03-22T15:43:41.105+00:00"
}
```

---

### Невалидный JSON (400 Bad Request)

Возникает при ошибке в формате JSON.

**Запрос:**

```json
{
  "login": "ivan",
  "password": "123456,
  "email": "ivan@example.com",
  "phone": "79304100585",
  "fullNameLastName": "Ivan",
  "fullNameFirstName": "Ivanovich",
  "fullNameMiddleName": "Ivanov",
  "gender": "Male",
  "birthDate": "2006-03-22"
}
```

**Ответ:**

```json
{
  "status": "Bad Request",
  "message": "Invalid JSON",
  "timestamp": "2026-03-22T15:44:15.508+00:00"
}
```

## Примеры авторизации

### Успешная авторизация

**Запрос:**

```json
{
  "login": "ivan",
  "password": "123456"
}
```

**Ответ:**

```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1NUVU*Ijo0LCJzdWIiOiJpdmFuIiwiaW*Tk1MjIwLCJleHAiOjE3NzQxOTg4MjB9.tE3vyYKX4P*gD6lCWY*"
}
```

### Неверный логин или пароль (401 Unauthorized)

**Запрос:**

```json
{
  "login": "iva",
  "password": "123456"
}
```

**Ответ:**

```json
{
  "status": "Unauthorized",
  "message": "Invalid login or password",
  "timestamp": "2026-03-22T15:59:21.201+00:00"
}
```

## Примеры обновления данных студента

### ✅ Успешное обновление данных

**Запрос:**

```json id="j7t0zx"
{
  "birthDate": "2006-03-22"
}
```

**Ответ:**

```text id="g8q3ra"
HTTP 200 OK
```

---

### Невалидный JSON (400 Bad Request)

Возникает при ошибке в формате JSON.

**Запрос:**

```json id="p2x9rk"
{
  "fullNameLastName": ",
  "birthDate": "2026-03-22"
}
```

**Ответ:**

```json id="l9r2js"
{
  "status": "Bad Request",
  "message": "Invalid JSON",
  "timestamp": "2026-03-22T17:29:35.916+00:00"
}
```

### Ошибка валидации (422 Unprocessable Entity)

Возникает при передаче некорректных данных (например, пустые обязательные поля).

**Запрос:**

```json id="6mfvcz"
{
  "fullNameLastName": "",
  "birthDate": "2026-03-22"
}
```

**Ответ:**

```json id="8x6o0l"
{
  "status": "Unprocessable Entity",
  "message": "Request validation failed",
  "timestamp": "2026-03-22T17:31:14.028+00:00"
}
```

### Студент не найден при обновлении (404 Not Found)

Возникает при попытке обновить несуществующего студента.

**Запрос:**

```text id="m3x9po"
PATCH http://localhost:8080/cipinagora/api/student/700/edit
```

**Ответ:**

```json id="z4n2yk"
{
  "status": "Not Found",
  "message": "Student with id 700 not found",
  "timestamp": "2026-03-22T17:35:30.387+00:00"
}
```

## Примеры удаления студента

### Успешное удаление (204 No Content)

Студент и связанный пользователь успешно удаляются из системы.

**Запрос:**

```text id="p7r2vb"
DELETE http://localhost:8080/cipinagora/api/student/4/delete
```

**Ответ:**

```text id="c9m4yt"
HTTP 204 No Content
```

### Студент не найден (404 Not Found)

Возникает при попытке удалить несуществующего студента.

**Запрос:**

```text id="k1x8mq"
DELETE http://localhost:8080/cipinagora/api/student/700/delete
```

**Ответ:**

```json id="w3n9dz"
{
  "status": "Not Found",
  "message": "Student with id 700 not found",
  "timestamp": "2026-03-22T17:38:57.646+00:00"
}
```

### Неверный HTTP метод (405 Method Not Allowed)

Возникает, если используется неподдерживаемый HTTP-метод для данного эндпоинта.

**Запрос:**

```text id="3xj2pl"
POST http://localhost:8080/cipinagora/api/student/3/delete
```

(эндпоинт ожидает `DELETE`, а не `POST`)

**Ответ:**

```json id="9v2kqw"
{
  "status": "Method Not Allowed",
  "message": "HTTP method is not supported for this endpoint",
  "timestamp": "2026-03-22T17:41:29.521+00:00"
}
```

---

## Структура проекта

https://github.com/edlwss/sporta.git