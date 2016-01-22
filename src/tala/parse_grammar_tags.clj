(ns tala.parse-grammar-tags
  (:use [clojure.string :only (ends-with? includes?)]))

(def cases {:nominative "NF" :accusative "ÞF" :dative "ÞGF" :genitive "EF"})
(def numbers {:singular "ET" :plural "FT"})

(defn get-matching-keyword [tag col]
  (key (first (filter #(includes? tag (val %)) col))))

(defn get-article [tag]
  (if (ends-with? tag "gr")
      :definite
      :indefinite))

(defn parse-tag [tag]
  (def in (partial get-matching-keyword tag))
  {:case (in cases) :number (in numbers) :article (get-article tag)})
