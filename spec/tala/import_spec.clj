(ns tala.import-spec
  (:require [speclj.core :refer :all]
            [tala.import :refer :all])
  (:use [clojure.pprint :only (pprint)]))

(def line "hestur;6179;kk;alm;hestur;NFET")
(def data (read-csv "resources/sample.csv"))

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
      (should= section "alm"))))

