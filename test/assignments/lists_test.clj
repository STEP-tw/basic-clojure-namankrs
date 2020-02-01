(ns assignments.lists-test
  (:require [clojure.test :refer :all]
            [assignments.lists :refer :all]))

(deftest lists
  (testing "map"
    (testing "identity with single coll"
      (is (= [1 2 3] (map' identity [1 2 3]))))))

(deftest filter'-test
  (are [x y] (= x y)
             `(1 2) (filter pos-int? `(-1 1 2))
             `(3 4) (filter (partial < 2) `(1 2 3 4))))

(deftest count'-test
  (are [x y] (= x y)
             0 (count' [])
             3 (count' [1 2 3])))

(deftest reverse'-test
  (testing "seqable values"
    (are [x y] (= x y)
               `(3 2 1) (reverse' [1 2 3])
               `() (reverse' [])
               `(1) (reverse' [1])))
  (testing "non-seqable values"
    (is (= nil (reverse' 1)))))

(deftest every?'-test
  (are [x y] (= x y)
             true (every?' zero? [0 0])
             false (every?' zero? [0 1])
             true (every?' zero? [])
             ))

(deftest some?'-test
  (are [x y] (= x y)
             false (some?' zero? [1 1])
             true (some?' zero? [0 1])
             ))

(deftest ascending?-test
  (are [x y] (= x y)
             true (ascending? [1 2 3 4])
             true (ascending? [1 2 2 3 4 4])
             false (ascending? [1 2 5 4])
             ))

(deftest transpose-test
  (are [x y] (= x y)
             [[:a :c] [:b :d]] (transpose [[:a :b] [:c :d]])
             [[:a :d :g] [:b :e :h] [:c :f :i]] (transpose [[:a :b :c] [:d :e :f] [:g :h :i]])))

(deftest difference-test
  (are [x y] (= x y)
             `(4 5) (difference [1 2 3] [3 4 5])
             `() (difference [1 2] [1 2])
             `() (difference [1 2 3] [])))

(deftest union-test
  (are [x y] (= x y)
             `(1 2 3 4) (union [1 2 3] [2 3 4])
             `(1 2 3) (union [1 2 3] [])
             `(1 2 3) (union [] [1 2 3])))


(deftest points-around-origin-test
  (testing "eight points around origin"
    (is (= `([0 -1] [0 1] [-1 0] [-1 -1] [-1 1] [1 0] [1 -1] [1 1]) (points-around-origin)))))

(deftest cross-product-test
  (are [x y] (= x y)
             [[1 3] [1 4] [2 3] [2 4]] (cross-product [1 2] [3 4])
             [[1 2] [1 3]] (cross-product [1 2] [2 3])))

(deftest double-up-test
  (are [x y] (= x y)
             `(1 1 2 2 3 3) (double-up [1 2 3])
             `() (double-up [])))

(deftest third-or-fifth-test
  (are [x y] (= x y)
             `(0) (third-or-fifth [0 1 2])
             `(0 3 5) (third-or-fifth [0 1 2 3 4 5])
             `(0 3 5 6 9 10) (third-or-fifth [0 1 2 3 4 5 6 7 8 9 10])))

(deftest sqr-of-the-first-test
  (is (= `(16 16 16) (sqr-of-the-first `(4 5 6)))))

(deftest split-comb-test
  (are [x y] (= x y)
             `(1 3 2 4) (split-comb [1 2 3 4])
             `(1 3 2 4 5) (split-comb [1 2 3 4 5])))

(deftest muted-thirds-test
  (are [x y] (= x y)
             `(1 2 0 4 5 0) (muted-thirds [1 2 3 4 5 6])
             `() (muted-thirds [])))

(deftest palindrome?-test
  (are [x y] (= x y)
             true (palindrome? [])
             true (palindrome? [\n \a \m \a \n])
             true (palindrome? [1 0 0 1])
             false (palindrome? [\n \a \m])))

(deftest index-of-test
  (are [x y] (= x y)
             0 (index-of [1 2 3 4] 1)
             2 (index-of [1 2 3 4] 3)
             -1 (index-of [1 2 3 4] 5)))

(deftest reduce'-test
  (testing "without initial value"
    (are [x y] (= x y)
               24 (reduce' * [2 3 4])
               14 (reduce' + [5 2 3 4 0])))
  (testing "with initial value"
    (are [x y] (= x y)
               24 (reduce' * 4 [2 3])
               14 (reduce' + 5 [2 3 4 0])
               )))
