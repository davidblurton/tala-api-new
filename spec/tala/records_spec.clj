(ns tala.records-spec
  (:require [speclj.core :refer :all]
            [tala.records])
  (:import  [tala.records Lemma Form])
  (:use [clojure.pprint :only (pprint)]))

(def hestur
  (Lemma. "hestur" 6179 "kk" "alm"
    [(Form. "hest" "ÞFET") (Form. "hesta" "EFFT")]))

(pprint hestur)

(describe "a test"
  (it "FIXME, I fail."
    (should= 1 1)))
