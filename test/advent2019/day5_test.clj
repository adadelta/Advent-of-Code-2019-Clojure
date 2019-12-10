(ns advent2019.day5-test
  (:require [clojure.test :refer :all]
            [advent2019.day5 :refer :all :as day5]))

(deftest get-value-from-memory-test
  (testing "Computer: {:pointer 0 :memory [4 5 6 7 8 9] :output [] :input 1}. Index: 3. Should return 7"
    (is (= (day5/get-value-from-memory {:pointer 0 :memory [4 5 6 7 8 9] :output [] :input 1} 3) 7)))
  (testing "Computer: {:pointer 0 :memory [4 5 6 7 8 9] :output [] :input 1}. Index: 0. Should return 4"
    (is (= (day5/get-value-from-memory {:pointer 0 :memory [4 5 6 7 8 9] :output [] :input 1} 0) 4))))

(deftest get-value-from-memory-with-offset-test
  (testing "Computer: {:pointer 1 :memory [4 5 6 7 8 9] :output [] :input 1}. Offset: 1. Should return 5"
    (is (= (day5/get-value-from-memory-with-offset {:pointer 0 :memory [4 5 6 7 8 9] :output [] :input 1} 1) 5)))
  (testing "Computer: {:pointer 1 :memory [4 5 6 7 8 9] :output [] :input 1}. Offset: 2. Should return 6"
    (is (= (day5/get-value-from-memory-with-offset {:pointer 0 :memory [4 5 6 7 8 9] :output [] :input 1} 2) 6))))

(deftest set-value-in-memory-test
  (testing "Computer: {:pointer 1 :memory [4 5 6 7 8 9] :output [] :input 1}. Index: 1. Value: 15. Should return [4 15 6 7 8 9]"
    (is (= (day5/set-value-in-memory {:pointer 0 :memory [4 5 6 7 8 9] :output [] :input 1} 1 15) {:pointer 0 :memory [4 15 6 7 8 9] :output [] :input 1})))
  (testing "Computer: {:pointer 1 :memory [4 5 6 7 8 9] :output [] :input 1}. Index: 4. Value:  0. Should return [4 15 6 7 0 9]"
    (is (= (day5/set-value-in-memory {:pointer 0 :memory [4 5 6 7 8 9] :output [] :input 1} 4 0) {:pointer 0 :memory [4 5 6 7 0 9] :output [] :input 1}))))

(deftest get-operation-from-opcode-test
  (testing "Opcode: 1101. Should return 1"
    (is (= (day5/get-operation-from-opcode 1101) 1))
  (testing "Opcode: 1002. Should return 1"
    (is (= (day5/get-operation-from-opcode 1002) 2)))))

(deftest get-parameter-modes-from-opcode-test
  (testing "Opcode: 1101. Should return 11"
    (is (= (day5/get-parameter-modes-from-opcode 1101) 11))
  (testing "Opcode: 1002. Should return 10"
    (is (= (day5/get-parameter-modes-from-opcode 1002) 10)))))

(deftest parse-opcode-test
  (testing "Opcode: 1101. Should return 11"
    (is (= (day5/get-parameter-modes-from-opcode 1101) 11))
  (testing "Opcode: 1002. Should return 10"
    (is (= (day5/get-parameter-modes-from-opcode 1002) 10)))))

(deftest immediate-mode?-test
  (testing "parameter-modes: 11. Position: 1. Should return true"
    (is (= (day5/immediate-mode? 11 1) true)))
  (testing "parameter-modes: 10. Position: 1. Should return true"
    (is (= (day5/immediate-mode? 10 1) false)))
  (testing "parameter-modes: 10. Position: 1. Should return true"
    (is (= (day5/immediate-mode? 11 2) true))))

(deftest get-parameter-value-test
  (testing "Computer: {:pointer 0 :memory [1101 5 6 7 8 9] :output [] :input 1}. Position: 1. Should return 5"
    (is (= (day5/get-parameter-value {:pointer 0 :memory [1101 5 6 7 8 9] :output [] :input 1} 1) 5)))
  (testing "Computer: {:pointer 0 :memory [1001 5 6 7 8 9] :output [] :input 1}. Position: 1. Should return 9"
    (is (= (day5/get-parameter-value {:pointer 0 :memory [1001 5 6 7 8 9] :output [] :input 1} 1) 9))))

(deftest step-test
  (testing "Computer: {:pointer 0 :memory [1101 5 6 7 8 9] :output [] :input 1}. step-size: 2. Should return a computer with a pointer to 4"
    (is (= (day5/step {:pointer 0 :memory [1 5 6 7 8 9] :output [] :input 1} 4) {:pointer 4 :memory [1 5 6 7 8 9] :output [] :input 1}))))

(deftest create-computer-test
  (testing "Memory: [1 2 3]. Should return a computer with pointer 0, memory [1 2 3], output: [] and input: 1"
    (is (= (day5/create-computer [1 2 3]) {:pointer 0 :memory [1 2 3] :output [] :input 1}))))

(deftest binary-operation-test
  (testing "Computer: {:pointer 0 :memory [1101 5 6 3 8 9] :output [] :input 1}. Operation: +. Should return {:pointer 4 :memory [1101 5 6 11 8 9] :output [] :input 1}"
    (is (= (day5/binary-operation {:pointer 0 :memory [1101 5 6 3 8 9] :output [] :input 1} +) {:pointer 4 :memory [1101 5 6 11 8 9] :output [] :input 1})))
  (testing "Computer: {:pointer 0 :memory [1101 5 6 3 8 9] :output [] :input 1}. Operation: *. Should return {:pointer 4 :memory [1101 5 6 30 8 9] :output [] :input 1}"
    (is (= (day5/binary-operation {:pointer 0 :memory [1101 5 6 3 8 9] :output [] :input 1} *) {:pointer 4 :memory [1101 5 6 30 8 9] :output [] :input 1}))))

(deftest input-operation-test
  (testing "Computer: {:pointer 0 :memory [3 5 6 3 8 9] :output [] :input 1}. Value: 33. Should return {:pointer 2 :memory [3 5 6 3 8 33] :output [] :input 1}"
    (is (= (day5/input-operation {:pointer 0 :memory [3 5 6 3 8 9] :output [] :input 1} 33) {:pointer 2, :memory [3 5 6 3 8 33], :output [], :input 1}))))

(deftest output-operation-test
  (testing "Computer: {:pointer 0 :memory [4 5 6 3 8 9] :output [] :input 1}. Should return {:pointer 2, :memory [4 5 6 3 8 9], :output [9], :input 1}"
    (is (= (day5/output-operation {:pointer 0 :memory [4 5 6 3 8 9] :output [] :input 1}) {:pointer 2, :memory [4 5 6 3 8 9], :output [9], :input 1}))))