(ns tala.import-spec
  (:require [speclj.core :refer :all]
            [tala.import :refer :all])
  (:use [clojure.pprint :only (pprint)]))

(def data (read-csv "resources/sample.csv"))

(describe "Import data from a csv"
  (it "should read 16 records from sample"
    (should= 16 (count data)))

  (it "should create a lemma from sample data"
    (def hestur-lemma (csv->Lemma data))
    (should= "hestur" (:head-word hestur-lemma))
    (should= 6179 (:id hestur-lemma))))
