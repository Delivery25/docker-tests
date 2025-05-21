# Автотесты банковской системы

## Запуск тестов на Selenium Grid

Проект настроен для параллельного запуска тестов на нескольких браузерах через Selenium Grid 4.

### Предварительные условия

1. Должен быть запущен Selenium Grid 4 на `localhost:4444`
2. В гриде должны быть зарегистрированы браузеры:
   - Google Chrome
   - Firefox
   - Edge

### Способы запуска тестов

#### 1. Через батник

Просто запустите скрипт `run-tests.bat` в корне проекта:

```
run-tests.bat
```

#### 2. Через Maven

```
mvn clean test
```

#### 3. Через IntelliJ IDEA

1. Откройте файл `src/test/resources/testng.xml`
2. Нажмите правой кнопкой мыши и выберите "Run"

### Настройка конфигурации

#### Изменение URL Selenium Grid

Если ваш Selenium Grid запущен на другом URL, отредактируйте файл `src/test/resources/config.properties`:
   ```
   # Настройки для Selenium Grid
   remote=true
   gridUrl=http://ваш_url:4444/wd/hub
   ```

#### Изменение набора браузеров

Для изменения списка браузеров отредактируйте файл `src/test/resources/testng.xml`.
Добавьте, удалите или измените следующие блоки:

```xml
<test name="Firefox Tests">
    <parameter name="browser" value="firefox"/>
    <classes>
        <class name="com.globalsqa.banking.tests.CustomerTests"/>
        <class name="com.globalsqa.banking.tests.BankManagerTests"/>
    </classes>
</test>
```

#### Настройка параллельного выполнения

Для изменения количества одновременно используемых браузеров:

1. Измените параметр `thread-count` в `testng.xml`:
   ```xml
   <suite name="ParallelBrowserSuite" parallel="methods" thread-count="6">
   ```

#### Авторы

[Дудникова Диана](https://github.com/Delivery25/) и [Барабанов Илья](https://github.com/icepow-r/)