Автотесты

Цель практической работы
Закрепить на практике навыки работы с lambda-выражениями и Stream API.

Что нужно сделать
Вам предстоит реализовать методы для выборки различных данных из класса Airport.

Выполните задание в репозитории java_basics в проекте StreamAPI/Airport.

В классе Main содержатся статические методы, которые вам необходимо реализовать, используя Stream API и lambda-выражения.
Используйте для работы с аэропортом подключённую библиотеку com.skillbox.airport. Перед написанием кода внимательно изучите классы и методы библиотеки.
Запустите в проекте тесты и убедитесь, что они выполняются успешно. Если тесты не выполняются и выдают информацию об ошибках, постарайтесь самостоятельно исправить эти ошибки и добиться успешного выполнения тестов перед отправкой результатов на проверку.
Методы для реализации

long findCountAircraftWithModelAirbus(Airport airport, String model)
Найдите в переданном аэропорту среди прибывающих, вылетающих и припаркованных самолётов количество самолётов указанной модели. Модель самолёта — всегда в начале его имени.
Map<String, Integer> findMapCountParkedAircraftByTerminalName(Airport airport)
Найдите в переданном аэропорту количество припаркованных самолётов для каждого терминала. Возвращаемая Map в виде ключа содержит имя терминала, а в виде значения — количество припаркованных самолётов.
List<Flight> findFlightsLeavingInTheNextHours(Airport airport, int hours)
Найдите все рейсы, вылетающие в указанное количество часов от текущего времени.
Optional<Flight> findFirstFlightArriveToTerminal(Airport airport, String terminalName)
Найдите ближайший прилетающий в указанный терминал рейс.

Критерии оценки
Принято — тесты успешно выполняются.
На доработку — тесты не выполняются, задание не выполнено или выполнено неточно.