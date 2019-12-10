(ns advent2019.day5
  (:gen-class))

;;
;; Day 5: Sunny with a Chance of Asteroids
;;
;; Objective: Get the air conditioner working
;;

;;
;; Puzzle 1
;;

;; Program

(def program (slurp "./resources/day5Data"))

;; Utilities

(defn get-value-from-memory
  "Returns a value from memory by index"
  [computer index]
  (get-in computer [:memory index]))

(defn get-value-from-memory-with-offset
  "Returns a value from memory according to it's current pointer and an offset"
  [computer offset]
  (get-value-from-memory computer (+ (:pointer computer) offset)))

(defn set-value-in-memory
  "Sets a value in memory"
  [computer index value]
  (assoc-in computer [:memory index] value))

;; Opcode functions

(defn get-operation-from-opcode
  "Gets the operation (last digit) from an opcopde"
  [opcode]
  (mod opcode 100))

(defn get-parameter-modes-from-opcode
  "Gets the parameter modes (first twi digits) from an opcode"
  [opcode]
  (quot opcode 100))

(defn parse-opcode
  "Parses an opcode and returns a map with the operation and parameter modes"
  [computer]
  (let [opcode (get-value-from-memory-with-offset computer 0)
        operation (get-operation-from-opcode opcode)
        parameter-modes (get-parameter-modes-from-opcode opcode)]
    {:operation operation
     :parameter-modes parameter-modes}))

(defn immediate-mode?
  "Checks if a position (either 1 or 2 is immediate)"
  [parameter-modes position]
  (= 1.0 (mod (quot parameter-modes (Math/pow 10 (dec position))) 10)))

(defn get-parameter-value
  "Gets a parameters value (either positional or immediate)"
  [computer position]
  (if (immediate-mode? (:parameter-modes (parse-opcode computer)) position)
    (get-value-from-memory-with-offset computer position)
    (get-value-from-memory computer (get-value-from-memory-with-offset computer position))))

;; Computer

(defn step
  "Take a step 'forward' in the memory"
  [computer step-size]
  (update computer :pointer (partial + step-size)))

(defn create-computer
  "Creates a computer with memory, a memory location pointer and an output"
  [memory]
  {:pointer 0 :memory (vec memory) :output [] :input 1})

;; Operations

(defn binary-operation
  "Performs + and * operations"
  [computer operation]
  (let [first-value (get-parameter-value computer 1)
        second-value (get-parameter-value computer 2)
        result (operation first-value second-value)
        destination (get-value-from-memory-with-offset computer 3)]
    (-> computer
        (set-value-in-memory destination result)
        (step 4))))

(defn input-operation
  "Performs a set value (input) operation"
  [computer value]
  (-> computer
      (set-value-in-memory (get-value-from-memory-with-offset computer 1) value)
      (step 2)))

(defn output-operation
  "Returns a value from memory"
  [computer]
  (-> computer
      (update :output conj (get-parameter-value computer 1))
      (step 2)))

(output-operation {:pointer 0 :memory [4 5 6 3 8 9] :output [] :input 1})

(loop [computer (->> (clojure.string/split program #",")
                     (map read-string)
                     (create-computer))]
  (let [opcode (parse-opcode computer)]
    (case (:operation opcode)
      1 (recur (binary-operation computer +))
      2 (recur (binary-operation computer *))
      3 (recur (input-operation computer (:input computer)))
      4 (recur (output-operation computer))
      99 (:output computer))))