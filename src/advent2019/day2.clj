(ns advent2019.day2
  (:gen-class))

;;
;; Day 2: 1202 Program Alarm
;;
;; Objective: Fix computer
;;

;;
;; Puzzle 1
;;

(defn opcode-operator
  "Determines if an operator is +, * or nothing"
  [operator]
  (cond (= operator 1) +
        (= operator 2) *
        :else nil))

(defn get-vector-value
  "Gets a value from a vector based on a specific positions value"
  [vector position]
  (get vector (get vector position)))

(defn perform-opcode-operation
  "Performs an opcode arithmatic operation from a given start point and returns the result"
  [vector start]
  ((opcode-operator (get vector start)) (get-vector-value vector (+ start 1)) (get-vector-value vector (+ start 2))))

(defn run-opcode-instruction
  "Performs a full opcode instruction"
  [vector start]
  (assoc vector (get vector (+ start 3)) (perform-opcode-operation vector start)))

(defn run-intcode-program
  "Runs an intcode program"
  [vector start-point]
  (if (= 99 (get vector start-point))
    vector
    (run-intcode-program (run-opcode-instruction vector start-point) (+ start-point 4))))

(defn intcode->vector!
  "Reads the intcode and produces a vector of optcodes"
  [file]
  (mapv read-string (clojure.string/split (slurp file) #",")))

(defn fix-computer
  "Fixes the damn computer"
  [file]
  (run-intcode-program (intcode->vector! file) 0))

;;
;; Puzzle 2
;;
;; Determine what pair of inputs produces the output 19690720.

(defn find-noun-and-verb
  [vector output]
  (for [noun (range 100)
        verb (range 100)
        :let [result (run-intcode-program (assoc vector 1 noun 2 verb) 0)]
        :when (= output (first result))]
    {:noun noun :verb verb :output (+ (* 100 noun) verb)}))