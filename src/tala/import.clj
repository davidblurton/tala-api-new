(ns tala.import
  (:require [clojure.java.io :as io]
            [tala.records])
  (:import  [tala.records Lemma Form])
  (:use [clojure.string :only (split)]))

(def separator #";")

(defn read-csv [path]
  (with-open [reader (io/reader path)]
    (doall
      (line-seq reader))))

(defn line->Form [line]
  (let [[_ _ _ _ form grammar-tag] (split line separator)]
    (Form. form grammar-tag)))

(defn line->Lemma [line]
  (let [[head-word id word-class section] (split line separator)]
    (Lemma. head-word (Integer. id) word-class section [])))
