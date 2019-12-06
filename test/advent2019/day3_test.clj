(ns advent2019.day3-test
  (:require [clojure.test :refer :all]
            [advent2019.day3 :refer :all :as day3]))

(def test-wires (day3/read-data "./resources/day3TestData"))
(def test-wire1 (map day3/parse-wires (first test-wires)))
(def test-wire2 (map day3/parse-wires (second test-wires)))

(def test2-wires (day3/read-data "./resources/day3TestData2"))
(def test2-wire1 (map day3/parse-wires (first test2-wires)))
(def test2-wire2 (map day3/parse-wires (second test2-wires)))

(deftest step-test
  (testing "Current: [0 0], Sub-path: [L 2]. Should return ([-1 0] [-2 0])"
    (is (= (day3/step [0 0] ["L" 2]) (list [-1 0] [-2 0]))))
  (testing "Current: [0, 0], Sub-path: [U 3]. Should return ([0 1] [0 2] [0 3] [0 4])"
    (is (= (day3/step [0, 0] ["U" 4]) (list [0 1] [0 2] [0 3] [0 4]))))
  (testing "Current: [0, 0], Sub-path: [R 2]. Should return ([1 0] [2 0])"
    (is (= (day3/step [0, 0] ["R" 2]) (list [1 0] [2 0]))))
  (testing "Current: [0, 0], Sub-path: [D 3]. Should return ([0 -1] [0 -2] [0 -3])"
    (is (= (day3/step [0, 0] ["D" 3]) (list [0 -1] [0 -2] [0 -3])))))

(deftest find-nearest-crossed-wires-test
  (testing "TestData: Should return 159"
    (is (= (day3/find-nearest-crossed-wires test-wire1 test-wire2) 159)))
  (testing "TestData 2: Should return 159"
    (is (= (day3/find-nearest-crossed-wires test2-wire1 test2-wire2) 135)))
  (testing "Data: Should return 731"
    (is (= (day3/find-nearest-crossed-wires day3/wire1 day3/wire2) 731))))

(deftest find-lest-delayed-crossed-wires-test
  (testing "TestData: Should return 610"
    (is (= (day3/find-lest-delayed-crossed-wires test-wire1 test-wire2) 610)))
  (testing "TestData 2: Should return 410"
    (is (= (day3/find-lest-delayed-crossed-wires test2-wire1 test2-wire2) 410)))
  (testing "Data: Should return 5672"
    (is (= (day3/find-lest-delayed-crossed-wires day3/wire1 day3/wire2) 5672))))