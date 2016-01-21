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

(defn parse-line [line]
  (let [[head-word id word-class section] (split line separator)]
    (tala.records/->Lemma head-word (Integer. id) word-class section nil)))

(defn csv->Lemma [data]
  (parse-line (first data)))
