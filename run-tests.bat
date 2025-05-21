@echo off
echo Запуск тестов банковской системы на всех браузерах через Selenium Grid
echo Grid URL: http://localhost:4444/wd/hub

echo Запуск тестов...
call mvn clean test

echo Тестирование завершено. Отчеты доступны в директории: target/selenide-reports 