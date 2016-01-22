(ns tala.core
  (:use compojure.core)
  (:require [tala.import]
            [tala.parse-grammar-tags :as parse]
            [ring.middleware.json :as middleware]
            [compojure.route :as route]
            [compojure.handler :as handler])
  (:use [clojure.pprint :only (pprint)]))

(defn mapTags [form]
  (parse/parse-tag (:grammar-tag form)))

(defn mapForms [forms]
  (map #(assoc % :tags (mapTags %)) forms))

(def data (tala.import/read-csv "resources/sample.csv"))
(def words (tala.import/parse-data data))
(def forms (mapForms (:forms words)))
(def result (assoc-in words [:forms] forms))

(defroutes app-routes
  (GET "/" [] {:body result})
  (route/not-found "<h1>Page not found</h1>"))

(def app
  (-> (handler/api app-routes)
      (middleware/wrap-json-body)
      (middleware/wrap-json-params)
      (middleware/wrap-json-response)))