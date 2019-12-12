(ns advent2019.day6-test
  (:require [clojure.test :refer :all]
            [advent2019.day6 :refer :all :as day6]))

(deftest load-orbits-test
  (testing "Data: COM)B B)C C)D D)E E)F B)G G)H D)I E)J J)K K)L. Should return {K J, L K, G B, J E, H G, E D, C B, F E, B COM, I D, D C}"
    (is (= (day6/load-orbits "./resources/day6TestData") {"K" "J", "L" "K", "G" "B", "J" "E", "H" "G", "E" "D", "C" "B", "F" "E", "B" "COM", "I" "D", "D" "C"}))))

(deftest count-orbits-test
  (testing "Planet: K. Orbits: {K J, L K, G B, J E, H G, E D, C B, F E, B COM, I D, D C}. Should return 6"
    (is (= (day6/count-orbits "J" {"K" "J", "L" "K", "G" "B", "J" "E", "H" "G", "E" "D", "C" "B", "F" "E", "B" "COM", "I" "D", "D" "C"}) 5)))
  (testing "Planet: COM. Orbits: {K J, L K, G B, J E, H G, E D, C B, F E, B COM, I D, D C}. Should return 0"
    (is (= (day6/count-orbits "COM" {"K" "J", "L" "K", "G" "B", "J" "E", "H" "G", "E" "D", "C" "B", "F" "E", "B" "COM", "I" "D", "D" "C"}) 0))))

(deftest count-total-orbits-test
  (testing "Orbits: {K J, L K, G B, J E, H G, E D, C B, F E, B COM, I D, D C}. Should return 42"
    (is (= (day6/count-total-orbits {"K" "J", "L" "K", "G" "B", "J" "E", "H" "G", "E" "D", "C" "B", "F" "E", "B" "COM", "I" "D", "D" "C"}) 42))))

(deftest find-route-to-COM-test
  (testing "Planet: D. Orbits: {K J, L K, G B, J E, H G, E D, C B, F E, B COM, SAN I, I D, D C, YOU K}. Should return #{C B D}"
    (is (= (day6/find-route-to-COM "D" {"K" "J", "L" "K", "G" "B", "J" "E", "H" "G", "E" "D", "C" "B", "F" "E", "B" "COM", "SAN" "I", "I" "D", "D" "C", "YOU" "K"}) #{"C" "B" "D"}))))

(deftest find-route-to-SAN-test
  (testing "Orbits: {K J, L K, G B, J E, H G, E D, C B, F E, B COM, SAN I, I D, D C, YOU K}. Should return 4"
    (is (= (day6/find-route-to-SAN {"K" "J", "L" "K", "G" "B", "J" "E", "H" "G", "E" "D", "C" "B", "F" "E", "B" "COM", "SAN" "I", "I" "D", "D" "C", "YOU" "K"}) 4))))