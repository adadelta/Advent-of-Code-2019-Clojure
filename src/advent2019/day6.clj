(ns advent2019.day6
  (:gen-class)
  (:require [clojure.set]))

;;
;; Day 6: Universal Orbit Map
;;

;; Puzzle 1.

(defn load-orbits
  "Loads and transforms orbits"
  [orbits]
  (->> orbits
       slurp
       clojure.string/split-lines
       (map #(reverse (clojure.string/split % #"\)")))
       (reduce (fn [orbits [outer inner]] (assoc orbits outer inner)) (hash-map))))

(defn count-orbits
  "Counts the total orbits and indirect orbits for a planet"
  [planet orbits]
  (count (take-while #(not= "COM" %) (iterate orbits planet))))

(defn count-total-orbits
  "Counts the total number of orbits in the system"
  [orbits]
  (let [total (atom 0)]
    (doseq [[k] orbits]
      (swap! total #(+ (count-orbits k orbits) %)))
    @total))


;; Puzzle 2.

(defn find-route-to-COM
  "Finds the route to COM"
  [planet orbits]
  (into #{} (take-while #(not= "COM" %) (iterate orbits planet))))

(defn find-route-to-SAN
  "Finds the route from 'YOU' to 'SAN'"
  [orbits]
  (let [santa-route (find-route-to-COM (get orbits "SAN") orbits)
        you-route (find-route-to-COM (get orbits "YOU") orbits)]
    ;; Union - Intersection gives us gives us the route between them
    (count (clojure.set/difference
            (clojure.set/union santa-route you-route)
            (clojure.set/intersection santa-route you-route)))))