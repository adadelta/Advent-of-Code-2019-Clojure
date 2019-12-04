(ns advent2019.day1-test
  (:require [clojure.test :refer :all]
            [advent2019.day1 :refer :all :as day1]))

(deftest calculate-fuel-requirement-test
  (testing "Mass: 12 - should return 2"
    (is (= (day1/calculate-fuel-requirement-for-module 12) 2)))
  (testing "Mass: 14 - should return 2"
    (is (= (day1/calculate-fuel-requirement-for-module 14) 2)))
  (testing "Mass: 1969 - should return 654"
    (is (= (day1/calculate-fuel-requirement-for-module 1969) 654)))
  (testing "Mass: 100756 - should return 33583"
    (is (= (day1/calculate-fuel-requirement-for-module 100756) 33583))))

(deftest calculate-total-fuel-requirement-test
  (testing "Calculate total fuel requirements for module masses 12 14 1969 199756. Should return 34241"
    (is (= (day1/calculate-total-fuel-requirement "./resources/day1TestData") 34241)))
  (testing "Calculate total fuel requirements for all puzzle modules. Should return 3394106"
    (is (= (day1/calculate-total-fuel-requirement "./resources/day1Data") 3394106))))

(deftest calculate-total-fuel-requirement2-test
  (testing "Calculate total fuel requirements (also taking in weight of fuel) for module with mass 14. Should return 2"
    (is (= (day1/calculate-fuel-requirement-for-module2 14) 2)))
  (testing "Calculate total fuel requirements (also taking in weight of fuel) for module with mass 1969. Should return 966"
    (is (= (day1/calculate-fuel-requirement-for-module2 1969) 966)))
  (testing "Calculate total fuel requirements (also taking in weight of fuel) for module with mass 100756. Should return 50346"
    (is (= (day1/calculate-fuel-requirement-for-module2 100756) 50346))))

(deftest calculate-total-fuel-requirement-test2
  (testing "Calculate total fuel requirements for module masses 12 14 1969 199756 as well as their fuel. Should return 51316"
    (is (= (day1/calculate-total-fuel-requirement2 "./resources/day1TestData") 51316)))
  (testing "Calculate total fuel requirements for all puzzle modules as well as their fuel. Should return 5088280"
    (is (= (day1/calculate-total-fuel-requirement2 "./resources/day1Data") 5088280))))