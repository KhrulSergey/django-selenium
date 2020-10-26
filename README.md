# Автоматизированное тестирование системы "Django Blog". / Automated testing of the "Django Blog" system. 
## Тест кейс "Login And CRUD BlogEntry" / Test case "Login And CRUD BlogEntry"
- Запустить тест кейс и формирование отчета можно командой из корневого каталога проекта: / You can start a test case and generate a report with a command from the root directory of the project:
 ```
mvn clean test io.qameta.allure:allure-maven:serve
```

При разработке тест кейса использован паттерн проектирования Page Object и Page Factory,
 а также Selenium WebDriver, TestNG и Spring Boot, Allure на Java (сборщик maven), который будет выполняет следующие тесты:
The test case use the Page Object and Page Factory design patterns , as well as Selenium WebDriver, TestNG and Spring Boot, Allure in Java (maven collector):
 
- Тест на открытие страницы авторизации в административную панель / Test for opening the login page in the admin panel
- Тест на авторизацию / Authorization test
- Тест на открытие административной панели / Test to open the admin panel
- Тест на переход на страницу создания новой записи в Блоге / Test for opening a new blog post creation page
- Тест на открытие страницы создания новой записи в Блоге / Test for filling (creating) a new blog post
- Тест на заполнение (создание) новой записи в Блоге / Test for filling (creating) a new blog post
- Тест на существования созданной ранее записи в Блоге в административной панели / Test for the existence of a previously created blog entry in the admin panel
- Тест на существования созданной ранее записи в Блоге на публичной странце / Test for the existence of a previously created blog post on a public page
- Тест на удаление ранее созданной записи в Блоге / Test for deleting a previously created blog post
- Тест на выход из административной панели / Test to exit the admin panel

 ## Получение исходного кода и старт проекта / Obtaining the source code and starting the project 
 
- Получить исходный код проекта командой / Get the project source code by the team
 ```
 git clone https://github.com/KhrulSergey/django-selenium
 ```
- Перейти в рабочую папку проекта / Go to the project working folder
`cd .\django-selenium`
- Запустить тесты командой / Run tests with command
`mvn clean test`
- Запустить тесты вместе с формирование отчета командой / Run tests along with generating a report with the command
`mvn clean test io.qameta.allure:allure-maven:serve`

 ## Конфигурация тест кейса / Test case configuration
 Конфигурация находится в файле / The configuration is file
 `src/main/resources/application.properties`
