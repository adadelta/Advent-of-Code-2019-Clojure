(ns advent2019.day1-test
  (:require [clojure.test :refer :all]
            [advent2019.day1 :refer :all]))

(deftest calculate-fuel-requirement-test
  (testing "Mass: 12 - should return 2"
    (is (= (advent2019.day1/calculate-fuel-requirement-for-module 12) 2)))
  (testing "Mass: 14 - should return 2"
    (is (= (advent2019.day1/calculate-fuel-requirement-for-module 14) 2)))
  (testing "Mass: 1969 - should return 654"
    (is (= (advent2019.day1/calculate-fuel-requirement-for-module 1969) 654)))
  (testing "Mass: 100756 - should return 33583"
    (is (= (advent2019.day1/calculate-fuel-requirement-for-module 100756) 33583))))

(deftest calculate-total-fuel-requirement-test
  (testing "Calculate total fuel requirements for module masses 12 14 1969 199756"
    (is (= (advent2019.day1/calculate-total-fuel-requirement "./resources/day1TestData") 34241)))
  (testing "Calculate total fuel requirements for all puzzle modules"
    (is (= (advent2019.day1/calculate-total-fuel-requirement "./resources/day1Data") 3394106))))
