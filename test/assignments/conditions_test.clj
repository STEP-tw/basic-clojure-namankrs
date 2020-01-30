(ns assignments.conditions-test
  (:require [clojure.test :refer :all]
            [assignments.conditions :refer :all]))

(deftest safe-division
  (testing "non zero denominator"
    (is (= 2 (safe-divide 4 2))))
  (testing "zero denominator"
    (is (nil? (safe-divide 3 0)))))


(deftest informative-divide-test
  (testing "non zero denominator"
    (is (= 2 (informative-divide 4 2))))
  (testing "zero denominator"
    (is (= :infinite (informative-divide 3 0)))))

(deftest harishchandra-test
  (testing "truthy value"
    (is (= 2 (harishchandra 2))))
  (testing "falsy values"
    (are [x y] (= x y)
               nil (harishchandra false)
               nil (harishchandra nil))))

(deftest yudishtira-test
  (testing "truthy values"
    (is (= 2 (yudishtira 2))))
  (testing "falsy values"
    (are [x y] (= x y)
               :ashwathama (yudishtira false)
               :ashwathama (yudishtira nil))))

(deftest duplicate-first-test
  (testing "coll with values"
    (are [x y] (= x y)
               [1 1 2] (duplicate-first [1 2])
               `(4 4 3) (duplicate-first `(4 3))))
  (testing "empty coll"
    (are [x y] (= x y)
               nil (duplicate-first [])
               nil (duplicate-first `()))))
