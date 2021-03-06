# March 2015 Functional Programming Contest by Roman Dushkin

This program implements some data transformations on hexapods habitat statistics.
Task description in Russian language is here in Roman's blog post: http://haskell98.blogspot.ru/2015/03/2015.html

Check out code in [core.clj](https://github.com/alexpetrov/fp-contest-data-transformations/blob/master/src/fp_contest_data_transformations/core.clj).

Check out tests to see basic examples of what algorithm is about [core_test.clj](https://github.com/alexpetrov/fp-contest-data-transformations/blob/master/test/fp_contest_data_transformations/core_test.clj).

## Usage

To run program enter following in console:

``` bash
./run.sh
```
It will compile program to one uberjar and run it.

To run tests:

``` bash
lein test
```

## Output

If you run this program, you will get this output in console:

```
Diversity of hexapods by countries:
{"Эритрея" 2,
 "Таруса" 1,
 "Йемен" 2,
 "Елабуга" 4,
 "Эфиопия" 5,
 "Малайзия" 2,
 "Зимбабве" 4,
 "Танганьика" 5,
 "Пакистан" 4,
 "Югославия" 2,
 "Шри-Ланка" 2,
 "Бутан" 1,
 "Сингапур" 4,
 "Филиппины" 4,
 "Албания" 2,
 "Курдистан" 1,
 "Германия" 2,
 "Ломбардия" 1,
 "Вевелония" 6,
 "Херсонес" 3,
 "Вайоминг" 2,
 "Парагвай" 2,
 "Индия" 3,
 "Дания" 3,
 "Ямайка" 3,
 "Тотьма" 1,
 "Уганда" 3,
 "Прерия" 3,
 "Кения" 4,
 "Танзания" 2,
 "Греция" 4,
 "Патагония" 2,
 "Россия" 5,
 "Непал" 2,
 "Япония" 4,
 "Сибирь" 2,
 "Австралия" 4,
 "Камчатка" 2,
 "Ливан" 3}
Dissapearance risks:
{"Популий грыжомельский" 1744,
 "Мелолонтий западный" 1814,
 "Десятилиньята лепая" 496,
 "Аурата сетуньская" 1314,
 "Семипунктата Коха" 1601,
 "Гортикола филоперьевая" 2151}
```

And csv file resources/hexapod-stats.csv with content like this:

```
Country/Hexapod;Популий грыжомельский;Мелолонтий западный;Десятилиньята лепая;Аурата сетуньская;Семипунктата Коха;Гортикола филоперьевая
Эритрея;-;-;Единицы;Сравнительно немного;-;-
Таруса;-;Много;-;-;-;-
Йемен;-;-;-;Сравнительно немного;Немного;-
Елабуга;Много;Очень мало;-;-;Много;Много
Эфиопия;Много;Единицы;-;Сравнительно немного;Единицы;Очень много
Малайзия;-;-;-;Сравнительно немного;Мало;-
Зимбабве;Единицы;-;Очень мало;-;Единицы;Очень мало
Танганьика;Очень много;Много;-;Сравнительно немного;Очень много;Очень мало
Пакистан;Немного;Очень мало;Немного;-;Немного;-
Югославия;Много;-;-;-;-;Много
Шри-Ланка;-;-;-;Много;Мало;-
Бутан;-;-;-;Много;-;-
Сингапур;Сравнительно немного;Много;-;-;Сравнительно немного;Много
Филиппины;Очень много;Единицы;Мало;-;-;Очень мало
Албания;Единицы;-;Очень мало;-;-;-
Курдистан;Много;-;-;-;-;-
Германия;-;-;-;В огромных количествах;Немного;-
Ломбардия;-;Мало;-;-;-;-
Вевелония;Сравнительно немного;Сравнительно немного;Немного;В огромных количествах;Много;Сравнительно немного
Херсонес;Много;-;-;-;Мало;Много
Вайоминг;Немного;-;-;-;-;Немного
Парагвай;-;Очень мало;Единицы;-;-;-
Индия;-;-;Единицы;Сравнительно немного;-;Сравнительно немного
Дания;-;Сравнительно немного;-;-;В огромных количествах;Сравнительно немного
Ямайка;-;-;Сравнительно немного;-;Очень много;Очень мало
Тотьма;-;Очень много;-;-;-;-
Уганда;-;-;Сравнительно немного;Сравнительно немного;-;Много
Прерия;Немного;-;Немного;-;-;Немного
Кения;Много;Единицы;-;В огромных количествах;-;Немного
Танзания;Единицы;-;-;В огромных количествах;-;-
Греция;Немного;-;-;В огромных количествах;Немного;Много
Патагония;Единицы;-;Единицы;-;-;-
Россия;Единицы;-;Мало;Мало;Очень мало;Единицы
Непал;Мало;-;-;Много;-;-
Япония;Единицы;Много;-;Сравнительно немного;Очень много;-
Сибирь;-;-;Мало;-;-;Много
Австралия;-;Сравнительно немного;Немного;Много;-;Единицы
Камчатка;-;-;-;Мало;-;Единицы
Ливан;В огромных количествах;Немного;-;-;-;В огромных количествах
```

## Acknowledgements

I want to say thank you to following developers for refactoring ideas:

- Alexey Pirogov [commit1](https://github.com/alexpetrov/fp-contest-data-transformations/commit/92004ad1830a548bcebf3cf1972f1f125c8cb2b1), [commit2](https://github.com/alexpetrov/fp-contest-data-transformations/commit/26b0151a1311697e6de37d0a3264a46af98c2206), [commit3](https://github.com/alexpetrov/fp-contest-data-transformations/commit/aa1ec596f0cfddadf48ac6f2b120b49737afe8d3)
- Dmitry Groshev [commit](https://github.com/alexpetrov/fp-contest-data-transformations/commit/24041c40397dd8f95de3249bdbedb10e4b8873ea)

## License

Copyright © 2015 Alexander Petrov (a.k.a. Lysenko by passport)

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
