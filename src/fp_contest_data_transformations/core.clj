(ns fp-contest-data-transformations.core
  (:gen-class)
  (:require [clojure.string :refer [split-lines split blank? trim join]]
            [clojure.pprint :refer [pprint print-table]]
            [clojure.java.io :as io]))

(defn parse-hexapod-description [desc]
  (let [[hexapod-name & hexapod-info] (split-lines desc)]
    {hexapod-name
     (into {}
           (for [stats hexapod-info
                 :let [stats-words (->> (split stats #"[:,]") (remove blank?) (map trim))
                       [quantity & countries] stats-words]
                 :when (not (blank? stats))]
             [quantity (into #{} countries)]))}))

(defn quantity-country->country-quantity [data]
  (into {}
        (for [[quantity countries] data
              country countries]
          [country quantity])))

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

(defn hexapod-stats->csv [data hexapods]
  (letfn [(header [hexapods]
                   (join ";" (conj (seq hexapods) "Country/Hexapod")))
           (body [data hexapods]
             (apply str
                    (interpose "\n"
                               (for [[country stats] data]
                                 (apply str
                                        (interpose ";"
                                                   (into [country]
                                                         (for [hexapod hexapods]
                                                           (get stats hexapod "-")))))))))]
          (apply str
                 (header hexapods)
                 "\n"
                 (body data hexapods))))

(defn risc-of-dissapearance [data countries quantities]
  (into {}
        (for [[hexapod stats] data]
          [hexapod (reduce +
                           (for [[country quantity] stats
                                 :when (and
                                        (not= "-" quantity)
                                        (get quantities quantity)
                                        (get countries country))]
                             (* (get countries country)
                                (get quantities quantity))))])))

(defn parse-number->descr [str]
  (let [lines (split-lines str)]
    (into {}
          (for [line lines
                :let [pair (split line #" ")
                      descr (trim (join " " (rest pair)))
                      number (Integer/parseInt (first pair))]]
            [descr number]))))

(defn -main
  [& args]
  (let [path "resources"
        frequencies (parse-number->descr (slurp (str path "/" "Frequencies.txt")))
        countries (parse-number->descr (slurp (str path "/" "States.txt")))
        data-files (filter #(.endsWith % ".dat") (.list (io/file path)))
        hexapod-quantity-country (apply merge (map #(parse-hexapod-description (slurp (str path "/" %))) data-files))
        hexapod-country-quantity (hexapod-quantity-country->hexapod-country-quantity hexapod-quantity-country)
        country-hexapod-quantity (hexapod-country-quantity->country-hexapod-quantity hexapod-country-quantity (keys countries))
        csv (hexapod-stats->csv country-hexapod-quantity (keys hexapod-quantity-country))
        diversity (country-hexapod-quantity->country-diversity country-hexapod-quantity)
        dissapearance-risc (risc-of-dissapearance hexapod-country-quantity countries frequencies)]
    (println "Diversity of hexapods by countries:")
    (pprint diversity)
    (println "Dissapearance risks:")
    (pprint dissapearance-risc)
    (spit (str path "/" "hexapod-stats.csv") csv)))
