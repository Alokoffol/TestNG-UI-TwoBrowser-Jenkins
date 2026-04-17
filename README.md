# TestNG-UI-TwoBrowser-Jenkins

## Описание
UI тесты интернет-магазина Saucedemo с использованием TestNG, параллельным запуском в 2 браузерах (Chrome и Яндекс.Браузер) и настройкой CI/CD в Jenkins.

## Технологии
- TestNG (параллельный запуск)
- Selenium WebDriver
- Allure Report
- Jenkins (CI/CD)
- Maven

## Запуск тестов
```bash
# Запуск всех тестов в 2 браузерах
mvn clean test

# Запуск с конкретным suite.xml
mvn clean test -DsuiteXmlFile=testng.xml

