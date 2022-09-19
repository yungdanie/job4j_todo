# job4j_todo
Проект представляет собой web-приложение для создания, отображения и управления задачами.
___
Функционал включает в себя взаимодействия с задачами: 
  + Добавление;
  + Изменение;
  + Переход на страницы задач;
  + Удаление

#### 
# Стек технологий
+ Spring boot
+ Thymeleaf 
+ Bootstrap
+ Hibernate 
+ PostgreSql 

#### Необходимые зависимости
| Зависимость | Версия | 
|------|:------:|
| PostgreSql | 42.5.0 |
| Hibernate core | 5.6.11 final |
| lombok 1.18.24 | 1.18.24 |
| JCIP | 1.0 |
| Spring Framework. Spring boot starter | 2.7.3 |
| Spring Framework. Spring Thymeleaf | --- |
| Spring Framework. Spring boot starter web | --- |
| Liquibase | 4.15.0 |

# Запуск проекта

  1) Для начала необходимо инициализировать базу данных PostgreSQL, данные для Hibernate назначаются в "pom.xml".
  
  2) По необходимости настроить liqubase назначаются в "db/liquibase.properties".

  3) В пакете проекта выполнить команду "mvn spring-boot:run".

# Изображения страниц приложения

*Главная страница*

![Image alt](https://github.com/yungdanie/job4j_todo/raw/master/src/main/resources/img/main_page.jpg)

*Добавление задачи*

![Image alt](https://github.com/yungdanie/job4j_todo/raw/master/src/main/resources/img/add_task.jpg)

*Страница задачи*

![Image alt](https://github.com/yungdanie/job4j_todo/raw/master/src/main/resources/img/task_page.jpg)

*Изменение задачи*

![Image alt](https://github.com/yungdanie/job4j_todo/raw/master/src/main/resources/img/edit_task.jpg)
