# run_tests_ps.ps1 - полная версия
# В самое начало файла
$PSDefaultParameterValues['Out-File:Encoding'] = 'utf8'
$PSDefaultParameterValues['*:Encoding'] = 'utf8'
[Console]::OutputEncoding = [System.Text.Encoding]::UTF8

Write-Host "Запуск тестов с поддержкой UTF-8" -Encoding UTF8
$OutputEncoding = [System.Text.Encoding]::UTF8
[Console]::OutputEncoding = [System.Text.Encoding]::UTF8
$env:JAVA_TOOL_OPTIONS = "-Dfile.encoding=UTF-8"

Write-Host "========================================" -ForegroundColor Cyan
Write-Host "   Запуск тестов с поддержкой UTF-8" -ForegroundColor Cyan
Write-Host "========================================" -ForegroundColor Cyan

# Запускаем через CMD с UTF-8
Write-Host "Запускаю тесты через CMD с UTF-8 кодировкой..." -ForegroundColor Yellow

$output = cmd /c "chcp 65001 > nul && .\gradlew.bat :public-apis:jsonplaceholder:test --info 2>&1"

# Выводим результат
$output

Write-Host "`n" + "="*40 -ForegroundColor Cyan

# Проверяем наличие кириллицы в выводе
if ($output -match "Не соответствует") {
    Write-Host "✅ Найдены русские сообщения в ассертах!" -ForegroundColor Green
    $output -split "`n" | Where-Object { $_ -match "Не соответствует" } | ForEach-Object {
        Write-Host "   Сообщение: $_" -ForegroundColor Green
    }
}

if ($LASTEXITCODE -eq 0) {
    Write-Host "`n✅ Все тесты прошли успешно!" -ForegroundColor Green
} else {
    Write-Host "`n❌ Некоторые тесты упали" -ForegroundColor Red
    Write-Host "   Подробный отчет: file:///C:/IdeaProjects/lessons/qa-automation-dojo/public-apis/jsonplaceholder/build/reports/tests/test/index.html" -ForegroundColor Yellow
}