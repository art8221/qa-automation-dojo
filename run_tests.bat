@echo off
echo ========================================
echo    Запуск тестов с поддержкой UTF-8
echo ========================================

echo Устанавливаю кодировку UTF-8...
chcp 65001 > nul

echo Запускаю тесты модуля jsonplaceholder...
call gradlew :public-apis:jsonplaceholder:test --info

echo.
if %errorlevel% equ 0 (
    echo ✅ Все тесты прошли успешно!
) else (
    echo ❌ Некоторые тесты упали
)

echo.
pause