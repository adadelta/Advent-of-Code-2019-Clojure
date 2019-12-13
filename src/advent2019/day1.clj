(ns advent2019.day1
  (:gen-class)
  (:require [clojure.java.io :as io]))

;;
;; Day 1: The Tyranny of the Rocket Equation
;;
;; Objective: Calculate required fuel to launch modules.
;;

;;
;; Puzzle 1
;;

(defn calculate-fuel-requirement-for-module
  "Calculates the fuel requirement for a model based on it's mass"
  [mass]
  (- (quot mass 3) 2))

(def calc-xf (comp (map read-string) (map calculate-fuel-requirement-for-module)))

(defn calculate-total-fuel-requirement
  "Loads module masses and calculates the total fuel requirement their mass"
  [file]
  (with-open [rdr (io/reader file)]
    (transduce calc-xf + (line-seq rdr))))

;;
;; Puzzle 2
;;

(defn- fuel-accumulator
  "Calculates fuel requirements for a module recursively"
  [accumulated-fuel new-fuel]
  (if (< new-fuel 0)
    accumulated-fuel
    (fuel-accumulator (+ accumulated-fuel new-fuel) (calculate-fuel-requirement-for-module new-fuel))))

(defn calculate-fuel-requirement-for-module2
  "Calculates the fuel requirement for a model based on it's mass as well as for the added fuel"
  [mass]
  (let [fuel-for-mass (calculate-fuel-requirement-for-module mass)]
    (fuel-accumulator fuel-for-mass (calculate-fuel-requirement-for-module fuel-for-mass))))

(def calc-xf2 (comp (map read-string) (map calculate-fuel-requirement-for-module2)))

(defn calculate-total-fuel-requirement2
  "Loads module masses and calculates the total fuel requirement for their mass, as well as the fuel itself"
  [file]
  (with-open [rdr (io/reader file)]
    (transduce calc-xf2 + (line-seq rdr))))