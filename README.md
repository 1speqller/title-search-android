# Movie Search

Приложение для поиска тайтлов с использованием [TVmaze API](https://www.tvmaze.com/api).

## Стек
- Kotlin
- Jetpack Compose (UI)
- Clean Architecture (Data, Domain, Presentation)
- Retrofit (сеть)
- Coroutines + Flow (асинхронность)
- Coil (загрузка изображений)

## Структура
- `data` — сеть, репозитории, мапперы
- `domain` — модели, интерфейсы репозиториев, интеракторы
- `presentation` — ViewModel, состояния экрана, UI

## Планы
- [ ] Детальный экран
- [ ] Навигация
- [ ] Кэширование через Room
- [ ] Пагинация
- [ ] Добавление других API (SteamDB для игр и т.п.)
- [ ] Тесты

## Статус
В активной разработке.
