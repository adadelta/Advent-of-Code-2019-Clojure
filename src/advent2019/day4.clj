(ns advent2019.day4
  (:gen-class))

;;
;; Day 4: Secure Container
;;
;; Objective: Find password
;;

;;
;; Puzzle 1
;;

(defn two-adjacent-numbers
  "Password must have at least two adjacent-numbers"
  [number-vector]
  (some #(>= (count %) 2) (partition-by identity number-vector)))

(defn never-decrease?
  "From left to right, the digits in a number may never decrease"
  [number-vector]
  (apply <= number-vector))

(defn is-eligible-password?
  "Checks if this number could be a possible password"
  [number]
  (let [password (mapv #(Character/digit % 10) (str number))]
    (and
     (= (count password) 6)
     (two-adjacent-numbers password)
     (never-decrease? password))))

(defn count-eligible-passwords
  "Checks if there are any valid passwords in range and returns the count"
  [start end]
  (count (filter is-eligible-password? (range start (inc end)))))

;;
;; Puzzle 2
;;

(defn two-adjacent-numbers2
  "Password must have at least two adjacent-numbers that are not part of a group of larger matching numbers"
  [number-vector]
  (some #(= (count %) 2) (partition-by identity number-vector)))

(defn is-eligible-password?2
  "Checks if this number could be a possible password"
  [number]
  (let [password (mapv #(Character/digit % 10) (str number))]
    (and
     (= (count password) 6)
     (never-decrease? password)
     (two-adjacent-numbers2 password))))

(defn count-eligible-passwords2
  "Checks if there are any valid passwords in range and returns the count"
  [start end]
  (count (filter is-eligible-password?2 (range start (inc end)))))