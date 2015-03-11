(defproject fp-contest-data-transformations "0.1.0-SNAPSHOT"
  :description "March 2015 Functional Programming contest project. It's all about Data Transformations"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0-alpha5"]
                 [expectations "2.0.9"]]
  :main ^:skip-aot fp-contest-data-transformations.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
