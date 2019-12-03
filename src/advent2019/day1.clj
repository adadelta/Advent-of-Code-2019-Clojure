(ns advent2019.day1
  (:gen-class))
(require '[clojure.java.io :as io])

;;
;; Day 1: The Tyranny of the Rocket Equation
;;
;; Objective: Calculate required fuel to launch modules.
;;

(defn calculate-fuel-requirement-for-module
  "Calculates the fuel requirement for a model based on it's mass"
  [number]
  (- (quot number 3) 2))

(def calc-xf (comp (map read-string) (map calculate-fuel-requirement-for-module)))

(defn calculate-total-fuel-requirement
  "Loads module masses and calculates the total fuel requirement for them"
  [file]
  (with-open [rdr (io/reader file)]
    (transduce calc-xf + (line-seq rdr))))