(ns tala.core
  (:gen-class :extends javax.servlet.http.HttpServlet)
  (:use compojure.core)
  (:require [tala.import]
            [tala.parse-grammar-tags :as parse]
            [ring.middleware.json :as middleware]
            [compojure.route :as route]
            [compojure.handler :as handler]
            [ring.util.servlet :as servlet]
            [ring.middleware.gzip]
            [clojure.java.io :as io]))

(defn mapTags [form]
  (parse/parse-tag (:grammar-tag form)))

(defn mapForms [forms]
  (map #(assoc % :tags (mapTags %)) forms))

(def data (tala.import/read-csv (io/resource "sample.csv")))
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
      (middleware/wrap-json-response)
      (ring.middleware.gzip/wrap-gzip)))

(servlet/defservice app)