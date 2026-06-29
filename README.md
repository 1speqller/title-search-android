# Movie Search

Приложение для поиска тайтлов с использованием [TVmaze API](https://www.tvmaze.com/api).

## Стек

- Kotlin
- Jetpack Compose (UI)
- Clean Architecture (Data, Domain, Presentation)
- Koin (Dependency Injection)
- Retrofit (сеть)
- Coroutines + Flow (асинхронность)
- SharedFlow (одноразовые события)
- Coil (загрузка изображений)

## Структура

- `data` — сеть, репозитории, мапперы
- `domain` — модели, интерфейсы репозиториев, интеракторы
- `presentation` — ViewModel, состояния экрана, UI
- `di` — модуль для внедрения зависимостей

## Функциональность

- [x] Поиск тайтлов по названию
- [x] Отображение результатов в виде сетки
- [x] Детальный экран с полной информацией о тайтле
- [x] Навигация между экранами (Activity)
- [x] Обработка состояний (Loading / Success / Error)
- [x] Одноразовые события (Snackbar через SharedFlow)
- [x] DI (Koin)

## Планы

- [ ] Кэширование через Room
- [ ] Пагинация (подгрузка при скролле)
- [ ] Добавление других API (SteamDB для игр и т.п.)
- [ ] Unit-тесты
- [ ] Тёмная тема

## Статус

В активной разработке.
