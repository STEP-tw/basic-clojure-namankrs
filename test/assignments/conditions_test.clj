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

(deftest five-point-someone-test
  (are [x y] (= x y)
             :chetan-bhagat (five-point-someone 0 5)
             :satan-bhagat (five-point-someone 5 0)
             :greece (five-point-someone 2 1)
             :universe (five-point-someone 1 2)))

(deftest repeat-and-truncate-test
  (testing "repeat"
    (is (= `(1 2 1 2) (repeat-and-truncate `(1 2) true false 0))))
  (testing "truncate"
    (is (= `(1 2) (repeat-and-truncate `(1 2 3 4) false true 2))))
  (testing "repeat and truncate"
    (is (= `(1 2 1 2) (repeat-and-truncate `(1 2) true false 0)))))

(deftest order-in-words-test
  (are [x y] (= x y)
             [:x-greater-than-y :y-greater-than-z] (order-in-words 4 3 2)
             [:x-greater-than-y :z-greater-than-x] (order-in-words 4 3 5)
             [:z-greater-than-x] (order-in-words 2 3 4)))

(deftest zero-aliases-test
  (are [x y] (= x y)
             :zero (zero-aliases 0)
             :empty (zero-aliases [])
             :empty (zero-aliases `())
             :empty-set (zero-aliases #{})
             :empty-map (zero-aliases {})
             :empty-string (zero-aliases "")
             :not-zero (zero-aliases "not-zero-value")))

(deftest zero-separated-palindrome-test
  (testing "list with values"
    (are [x y] (= x y)
               `(4 3 2 0 2 3 4) (zero-separated-palindrome [1 2 3])
               `(7 6 5 0 5 6 7) (zero-separated-palindrome [4 5 6]))))

(deftest conditions-apply-test
  (are [x y] (= x y)
             :wonder-woman (conditions-apply `(1 2 3))
             :durga (conditions-apply `(:a :b :c :d))
             :cleopatra (conditions-apply `([2 3] [4 5]))
             :tuntun (conditions-apply `(1 3 2)))
  )
