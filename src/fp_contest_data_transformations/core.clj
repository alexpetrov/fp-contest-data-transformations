(ns fp-contest-data-transformations.core
  (:gen-class)
  (:require [clojure.string :refer [split-lines split blank? trim]]
            [clojure.pprint :refer [pprint print-table]]))


(defn parse-hexapod-description [desc]
  (let [lines  (split-lines desc)
        hexapod-name (first lines)
        hexapod-info (remove blank? (rest lines))]
    [hexapod-name hexapod-info]
    {hexapod-name
     (into {}
           (for [stats hexapod-info
                 :let [stats-words (->> (split stats #"[:,]") (remove blank?) (map trim))
                       quantity (first stats-words)
                       countries (rest stats-words)]]
             [quantity (into #{} countries)]))}))

(comment

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
