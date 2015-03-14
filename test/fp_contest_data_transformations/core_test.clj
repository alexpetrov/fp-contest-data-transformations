(ns fp-contest-data-transformations.core-test
  (:use expectations)
  (:require [fp-contest-data-transformations.core :refer :all]))

(expect {"Аурата сетуньская" {"В огромных количествах" #{"Вевелония" "Германия"}
                              "Мало" #{"Камчатка" "Россия"}
                              "Много" #{"Австралия" "Бутан"}
                              "Сравнительно немного" #{"Индия" "Йемен"}}}
        (parse-hexapod-description
"Аурата сетуньская

В огромных количествах: Вевелония, Германия
Мало: Камчатка, Россия
Много: Австралия, Бутан
Сравнительно немного: Индия, Йемен"))

(expect {"Аурата сетуньская" {"Вевелония" "В огромных количествах"
                              "Германия" "В огромных количествах"
                              "Камчатка" "Мало"
                              "Россия" "Мало"}
         "Десятилиньята лепая" {"Индия" "В огромных количествах"
                                "Парагвай" "В огромных количествах"
                                "Филиппины" "Мало"
                                "Сибирь" "Мало"}}
        (hexapod-quantity-country->hexapod-country-quantity
         {"Аурата сетуньская" {"В огромных количествах" #{"Вевелония" "Германия"}
                               "Мало" #{"Камчатка" "Россия"}}
          "Десятилиньята лепая" {"В огромных количествах" #{"Индия" "Парагвай"}
                                 "Мало" #{"Филиппины" "Сибирь"}}}))

(expect {"Вевелония" "В огромных количествах"
         "Германия" "В огромных количествах"
         "Камчатка" "Мало"
         "Россия" "Мало"}
        (quantity-country->country-quantity
         {"В огромных количествах" #{"Вевелония" "Германия"}
          "Мало" #{"Камчатка" "Россия"}}))

(expect {"Вевелония" {"Аурата сетуньская" "В огромных количествах"
                      "Десятилиньята лепая" "-"}
         "Германия" {"Аурата сетуньская" "В огромных количествах"
                     "Десятилиньята лепая" "-"}
         "Индия" {"Аурата сетуньская" "-"
                  "Десятилиньята лепая" "В огромных количествах"}
         "Парагвай" {"Аурата сетуньская" "-"
                     "Десятилиньята лепая" "В огромных количествах"}}
        (hexapod-country-quantity->country-hexapod-quantity
         {"Аурата сетуньская" {"Вевелония" "В огромных количествах"
                               "Германия" "В огромных количествах"}
          "Десятилиньята лепая" {"Индия" "В огромных количествах"
                                 "Парагвай" "В огромных количествах"}}
         #{"Вевелония" "Германия" "Индия" "Парагвай"}))

(expect {"Вевелония" 2
         "Германия" 1
         "Индия" 1
         "Парагвай" 1}
        (country-hexapod-quantity->country-diversity
         {"Вевелония" {"Аурата сетуньская" "В огромных количествах"
                       "Десятилиньята лепая" "-"
                       "Гортикола филоперьевая" "Мало"}
          "Германия" {"Аурата сетуньская" "В огромных количествах"
                      "Десятилиньята лепая" "-"}
          "Индия" {"Аурата сетуньская" "-"
                   "Десятилиньята лепая" "В огромных количествах"}
          "Парагвай" {"Аурата сетуньская" "-"
                      "Десятилиньята лепая" "В огромных количествах"}}))

(expect
 "Country/Hexapod;Аурата сетуньская;Десятилиньята лепая;Гортикола филоперьевая
Германия;В огромных количествах;-;-
Вевелония;В огромных количествах;-;Мало"
 (hexapod-stats->csv
  {"Вевелония" {"Аурата сетуньская" "В огромных количествах"
               "Десятилиньята лепая" "-"
               "Гортикола филоперьевая" "Мало"}
  "Германия" {"Аурата сетуньская" "В огромных количествах"
              "Десятилиньята лепая" "-"}}
  #{"Аурата сетуньская" "Десятилиньята лепая" "Гортикола филоперьевая"}))

(expect
 {"Аурата сетуньская" (+ (* 2 100 ) (* 3 10))
  "Десятилиньята лепая" (+ (* 4 75) (* 5 1))}
 (risc-of-dissapearance
  {"Аурата сетуньская" {"Вевелония" "В огромных количествах"
                        "Германия" "Мало"}
   "Десятилиньята лепая" {"Индия" "Много"
                          "Парагвай" "Единицы"
                          "Гвинея" "-"
                          "Эквадор" "Несметное множество"
                          "Гваделупа" "Единицы"}}

  {"Вевелония" 2 "Германия" 3 "Индия" 4 "Парагвай" 5 "Гвинея" 1 "Эквадор" 1}
  {"В огромных количествах" 100 "Много" 75 "Мало" 10 "Единицы" 1}))

(expect
 {"В огромных количествах" 100 "Много" 75 "Мало" 10 "Единицы" 1}
 (parse-number->descr
  "100 В огромных количествах
75  Много
10  Мало
1   Единицы"))
