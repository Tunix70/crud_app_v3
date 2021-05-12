<h1>Задание</h1>

Необходимо реализовать консольное CRUD приложение, которое
взаимодействует с БД и позволяет выполнять все CRUD операции над
сущностями:

Writer (id, firstName, lastName, List<Post> posts, Region region)
  
Post (id, content, created, updated, PostStatus)

Region (id, name)

PostStatus(enum ACTIVE, DELETED)

Требования :

1. Придерживаться шаблона MVC (пакеты model, repository, service,
controller, view)
2. Для миграции БД использовать https://www.liquibase.org/
3. Сервисный слой приложения должен быть покрыт юнит тестами (junit, mockito).
4. Для импорта библиотек использовать Maven
5. 
Результатом выполнения проекта должен быть отдельный репозиторий на
github, с описанием задачи, проекта и инструкцией по локальному запуску
проекта.

Технологии : Java, MySQL, JDBC, Maven, Liquibase, JUnit, Mockito
