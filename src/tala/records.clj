(ns tala.records)

; Represents a single bin entry
(defrecord Lemma
  [head-word id word-class section])

; Represents a single declension or conjugation
(defrecord Form
  [form grammar-tag])
