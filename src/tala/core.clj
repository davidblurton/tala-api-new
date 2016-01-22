(ns tala.core
  (:require [tala.import]
            [tala.parse-grammar-tags :as parse])
  (:use [clojure.pprint :only (pprint)]))

(defn mapTags [form]
  (parse/parse-tag (:grammar-tag form)))

(defn mapForms [forms]
  (map #(assoc % :tags (mapTags %)) forms))

(defn -main
  "Start the tala api server"
  []
  (def data (tala.import/read-csv "resources/sample.csv"))
  (def words (tala.import/parse-data data))
  (def forms (mapForms (:forms words)))

  (pprint (assoc-in words [:forms] forms)))