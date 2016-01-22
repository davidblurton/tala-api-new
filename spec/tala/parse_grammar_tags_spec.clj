(ns tala.parse-grammar-tags-spec
  (:require [speclj.core :refer :all]
            [tala.parse-grammar-tags :refer :all])
  (:use [clojure.pprint :only (pprint)]))

(describe "Parse grammar tags"
  (it "for nouns"
    (should= {:case :nominative, :number :singular, :article :indefinite} (parse-tag "NFET"))
    (should= {:case :nominative, :number :plural, :article :indefinite} (parse-tag "NFFT"))
    (should= {:case :nominative, :number :plural, :article :definite} (parse-tag "NFFTgr"))
    (should= {:case :accusative, :number :singular, :article :indefinite} (parse-tag "ÞFET"))))
