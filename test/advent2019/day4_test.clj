(ns advent2019.day4-test
  (:require [clojure.test :refer :all]
            [advent2019.day4 :refer :all :as day4]))

(deftest two-adjacent-numbers-test
  (testing "Input: [1 2 3 2]. Should return false"
    (is (= (day4/two-adjacent-numbers [1 2 3 2]) nil)))
  (testing "Input: [1 2 3 4 2 2 5]. Should return true"
    (is (= (day4/two-adjacent-numbers [1 2 3 4 2 2 5]) true))))

(deftest never-decrease-test
  (testing "Input: [1 2 3 2]. Should return false"
    (is (= (day4/never-decrease? [1 2 3 2]) false)))
  (testing "Input: [1 2 3 4 5 5 6 6 6 7]. Should return false"
    (is (= (day4/never-decrease? [1 2 3 4 5 5 6 6 6 7]) true))))

(deftest is-eligible-password-test
  (testing "Input: 111111. Should return true"
    (is (= (day4/is-eligible-password? 111111) true)))
  (testing "Input: 223450. Should return false"
    (is (= (day4/is-eligible-password? 223450) false)))
  (testing "Input: 123789. Should return false"
    (is (= (day4/is-eligible-password? 123789) nil))))

(deftest count-eligible-passwords-test
  (testing "Input: 254032 and 789860. Should return 1033"
    (is (= (day4/count-eligible-passwords 254032 789860) 1033))))