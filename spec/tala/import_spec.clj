(ns tala.import-spec
  (:require [speclj.core :refer :all]
            [tala.import :refer :all])
  (:use [clojure.pprint :only (pprint)]))

(def data (read-csv "resources/sample.csv"))
(def line (first data))

(describe "Import data from a csv"
  (it "should read 16 records from sample"
    (should= 16 (count data)))

  (it "should create a form from a line"
    (let [{:keys [form grammar-tag]} (line->Form line)]
      (should= "hestur" form)
      (should= "NFET" grammar-tag)))

  (it "should create a lemma from a line"
    (let [{:keys [head-word id word-class section forms]} (line->Lemma line)]
      (should= "hestur" head-word)
      (should= 6179 id)
      (should= "kk" word-class)
      (should= section "alm")))

  (it "should map forms from data"
    (def forms (map line->Form data))
    (def hesta-forms (filter #(= "hesta" %) (map :form forms)))
    (should= 2 (count hesta-forms)))

  (it "should map lemmas from data"
    (let [{:keys [head-word forms]} (parse-data data)]
      (should= "hestur" head-word)
      (should= 16 (count forms)))))
