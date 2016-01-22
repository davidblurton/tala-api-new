(ns tala.import
  (:require [clojure.java.io :as io]
            [tala.records :refer :all])
  (:use [clojure.string :only (split)]))

(def separator #";")

(defn read-csv [path]
  (with-open [reader (io/reader path)]
    (doall
      (line-seq reader))))

(defn parse-line [line]
  (let [[head-word id word-class section form grammar-tag] (split line separator)]
    [head-word (Integer. id) word-class section form grammar-tag]))

(defn line->Form [line]
  (apply ->Form (drop 4 (parse-line line))))

(defn line->Lemma [line]
  (apply ->Lemma (take 4 (parse-line line))))

(defn parse-data [data]
  (assoc (line->Lemma (first data)) :forms (map line->Form data)))
