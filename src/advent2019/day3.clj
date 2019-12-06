(ns advent2019.day3
  (:gen-class))
(require '[clojure.data.csv :as csv]
         '[clojure.java.io :as io]
         '[clojure.set])

;;
;; Day 3: Crossed Wires
;;
;; Objective: Find the intersection point closest to the central port
;;

;;
;; Puzzle 1
;;

;; Prepare wires

(defn read-data
  "Reads the input and returns a list of vectors"
  [file]
  (with-open [reader (io/reader file)]
    (doall
     (csv/read-csv reader))))

(defn parse-wires
  "Parses a wire path"
  [wire-path]
  [(subs wire-path 0 1) (int (read-string (subs wire-path 1)))])

(def wires (read-data "./resources/day3Data"))
(def wire1 (map parse-wires (first wires)))
(def wire2 (map parse-wires (second wires)))

;; Wire functions

(defn step
  "Takes a step along a wire. Returns the next coordinates"
  [current-position next-sub-path]
  (let [[x1 y1] current-position
        direction (first next-sub-path)
        steps (second next-sub-path)]
    (for [step (range 1 (inc steps))]
      (condp = direction
      "L" [(- x1 step) y1]
      "U" [x1 (+ y1 step)]
      "R" [(+ x1 step) y1]
      "D" [x1 (- y1 step)]))))

(defn get-full-wire-path
  [wire]
  (reduce
   (fn [route next-sub-path]
     (concat route (step (last route) next-sub-path)))
   [[0 0]]
   wire))

(defn find-nearest-crossed-wires
  [wire1 wire2]
  (let [wire-path-1 (get-full-wire-path wire1)
        wire-path-2 (get-full-wire-path wire2)]
    ;; Get shortest distance (which should be the first one)
    (first
     ;; Sort
     (sort
      ;; Add the absolute values for x and y for each coordinate, this is the distance
      (map (fn [[x y]] (+ (Math/abs x) (Math/abs y)))
           ;; Remove center, as it would always be the nearest intersection
           (remove #{[0 0]}
                   ;; Find intersectons between both wires
                   (clojure.set/intersection
                    (set wire-path-1)
                    (set wire-path-2))))))))

;;
;; Puzzle 2
;;

;; What is the fewest combined steps the wires must take to reach an intersection?

(defn find-lest-delayed-crossed-wires
  [wire1 wire2]
  (let [wire-path-1 (get-full-wire-path wire1)
        wire-path-2 (get-full-wire-path wire2)]
    (first
     (sort
      ;; Here we find the index of the intersections (which indicates the step count)
      (map (fn [intersection] (+ (.indexOf wire-path-1 intersection) (.indexOf wire-path-2 intersection)))
           (remove #{[0 0]}
                   (clojure.set/intersection
                    (set wire-path-1)
                    (set wire-path-2))))))))