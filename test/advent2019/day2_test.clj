(ns advent2019.day2-test
  (:require [clojure.test :refer :all]
            [advent2019.day2 :refer :all :as day2]))

(deftest opcode-operator-test
  (testing "Opcode 1: Should return +"
    (is (= (day2/opcode-operator 1) +)))
  (testing "Opcode 2: Should return *"
    (is (= (day2/opcode-operator 2) *)))
  (testing "Opcode 7: Should return Nil"
    (is (= (day2/opcode-operator 7) nil))))

(deftest get-vector-value-test
  (testing "Vector [1 9 10 3 2 3 11 0 99 30 40 50] and position 1. Should return 30"
    (is (= (day2/get-vector-value [1 9 10 3 2 3 11 0 99 30 40 50] 1) 30)))
  (testing "Vector [1 9 10 3 2 3 11 0 99 30 40 50] and position 2. Should return 40"
    (is (= (day2/get-vector-value [1 9 10 3 2 3 11 0 99 30 40 50] 2) 40))))

(deftest perform-opcode-operation-test
  (testing "Performs the opcode operation on vector [1 9 10 3 2 3 11 0 99 30 40 50] in position 0. Should return 70"
    (is (= (day2/perform-opcode-operation [1 9 10 3 2 3 11 0 99 30 40 50] 0) 70)))
  (testing "Performs the opcode operation on vector [1 9 10 3 2 3 11 0 99 30 40 50] in position 4. Should return 150"
    (is (= (day2/perform-opcode-operation [1 9 10 3 2 3 11 0 99 30 40 50] 4) 150))))

(deftest run-opcode-instruction-test
  (testing "Runs an opcode instruction on vector [1 9 10 3 2 3 11 0 99 30 40 50] in position 0. Should return [1 9 10 70 2 3 11 0 99 30 40 50]"
    (is (= (day2/run-opcode-instruction [1 9 10 3 2 3 11 0 99 30 40 50] 0) [1 9 10 70 2 3 11 0 99 30 40 50]))))

(deftest find-noun-and-verb-test
  (testing "Find output when first opcode is 19690720 after run. Should return output: 6533"
    (is (:output (day2/find-noun-and-verb (day2/intcode->vector! "./resources/day2Data") 19690720) 6533))))