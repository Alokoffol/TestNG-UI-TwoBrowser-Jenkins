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
```

## Структура проекта

```
src/test/java/
├── base/
│   └── TestBase.java
├── constants/
│   └── AppConstants.java
├── driver/
│   ├── ChromeOptionsConfig.java
│   └── DriverFactory.java
├── listeners/
│   └── AllureListener.java
├── pages/
│   ├── BasePage.java
│   ├── CartPage.java
│   ├── CheckoutPage.java
│   ├── LoginPage.java
│   └── ProductsPage.java
├── resources/
│   └── logback-test.xml
└── tests/
    ├── CartTests.java
    ├── LoginTests.java
    └── PurchaseTest.java

```

## Jenkins

    Jenkinsfile содержит пайплайн для запуска тестов

    Параметризованная сборка (выбор браузера)

    Allure отчет после сборки