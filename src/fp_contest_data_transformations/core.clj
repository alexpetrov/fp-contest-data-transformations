(ns fp-contest-data-transformations.core
  (:gen-class)
  (:require [clojure.string :refer [split-lines split blank? trim]]
            [clojure.pprint :refer [pprint print-table]]))


(defn parse-hexapod-description [desc]
  (let [lines  (split-lines desc)
        hexapod-name (first lines)
        hexapod-info (remove blank? (rest lines))]
    {hexapod-name
     (into {}
           (for [stats hexapod-info
                 :let [stats-words (->> (split stats #"[:,]") (remove blank?) (map trim))
                       quantity (first stats-words)
                       countries (rest stats-words)]]
             [quantity (into #{} countries)]))}))

(defn quantity-country->country-quantity [data]
  (into {}
        (for [[quantity countries] data
              country countries]
          [country quantity])
        ))

(defn hexapod-quantity-country->hexapod-country-quantity [data]
  (into {}
        (for [[hexapod statistics] data]
          [hexapod (quantity-country->country-quantity statistics)])))

(defn hexapod-country-quantity->country-hexapod-quantity [data countries]
  (into {}
        (for [country countries]
          [country (into {}
                         (for [[hexapod stats] data]
                           [hexapod (get stats country "-")]))])))

(defn country-hexapod-quantity->country-diversity [data]
  (into {}
        (for [[country stats] data
              :let [hexapods (remove #(= (second %) "-") stats)
                    diversity (count (keys hexapods))]]
          [country diversity])))

(comment
  (country-hexapod-quantity->country-diversity
   {"Вевелония" {"Аурата сетуньская" "В огромных количествах"
                 "Десятилиньята лепая" "-"
                 "Гортикола филоперьевая" "Мало"}
    "Германия" {"Аурата сетуньская" "В огромных количествах"
                "Десятилиньята лепая" "-"}
    "Индия" {"Аурата сетуньская" "-"
             "Десятилиньята лепая" "В огромных количествах"}
    "Парагвай" {"Аурата сетуньская" "-"
                "Десятилиньята лепая" "В огромных количествах"}})
  (hexapod-country-quantity->country-hexapod-quantity
   {"Аурата сетуньская" {"Вевелония" "В огромных количествах"
                         "Германия" "В огромных количествах"}
    "Десятилиньята лепая" {"Индия" "В огромных количествах"
                           "Парагвай" "В огромных количествах"}}
   #{"Вевелония" "Германия" "Индия" "Парагвай"})

  (quantity-country->country-quantity
   {"В огромных количествах" #{"Вевелония" "Германия"}
    "Мало" #{"Камчатка" "Россия"}})

  (hexapod-quantity-country->hexapod-country-quantity
   {"Аурата сетуньская" {"В огромных количествах" #{"Вевелония" "Германия"}
                         "Мало" #{"Камчатка" "Россия"}}
    "Десятилиньята лепая" {"В огромных количествах" #{"Индия" "Парагвай"}
                           "Мало" #{"Филиппины" "Сибирь"}}})

  (split "semicolon: sdfsd, asdf, sdf" #"[:,]")
  (split-lines "asdf\n\nsemicolon: sdfsd, asdf, sdf")

  (parse-hexapod-description
   "Аурата сетуньская

В огромных количествах: Вевелония, Германия
Мало: Камчатка, Россия")

  )

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
