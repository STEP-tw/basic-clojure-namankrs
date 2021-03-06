(ns assignments.lists-test
  (:require [clojure.test :refer :all]
            [assignments.lists :refer :all]))

(deftest map'-test
  (testing "with single coll"
    (are [x y] (= x y)
               [1 2 3] (map' identity [1 2 3])
               '(2 4 6) (map' (partial * 2) '(1 2 3))))
  (testing "with multiple colls of equal length"
    (are [x y] (= x y)
               [4 16 25] (map' * [2 4 5] [2 4 5])
               [4 8 10] (map' + [2 4 5] [2 4 5])))
  (testing "with multiple colls of unequal length"
    (are [x y] (= x y)
               [4 20] (map' * [2 4 5] [2 5])
               [4 8] (map' + [2 4] [2 4 5]))))

(deftest filter'-test
  (are [x y] (= x y)
             '(1 2) (filter' pos-int? '(-1 1 2))
             '(3 4) (filter' (partial < 2) '(1 2 3 4))))

(deftest count'-test
  (are [x y] (= x y)
             0 (count' [])
             3 (count' [1 2 3])))

(deftest reverse'-test
  (testing "seqable values"
    (are [x y] (= x y)
               '(3 2 1) (reverse' [1 2 3])
               '() (reverse' [])
               '(1) (reverse' [1])))
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
             '(4 5) (difference [1 2 3] [3 4 5])
             '(3 4 5) (difference [1 2] [3 4 5 1 2])
             '() (difference [1 2] [1 2])
             '() (difference [1 2 3] [])))

(deftest union-test
  (are [x y] (= x y)
             '(1 2 3 4) (union [1 2 3] [2 3 4])
             '(1 2 3) (union [1 2 3] [])
             '(1 2 3) (union [] [1 2 3])))


(deftest points-around-origin-test
  (testing "eight points around origin"
    (is (= '([0 -1] [0 1] [-1 0] [-1 -1] [-1 1] [1 0] [1 -1] [1 1]) (points-around-origin)))))

(deftest cross-product-test
  (are [x y] (= x y)
             [[1 3] [1 4] [2 3] [2 4]] (cross-product [1 2] [3 4])
             [[1 2] [1 3]] (cross-product [1 2] [2 3])))

(deftest double-up-test
  (are [x y] (= x y)
             '(1 1 2 2 3 3) (double-up [1 2 3])
             '() (double-up [])))

(deftest third-or-fifth-test
  (are [x y] (= x y)
             '(0) (third-or-fifth [0 1 2])
             '(0 3 5) (third-or-fifth [0 1 2 3 4 5])
             '(0 3 5 6 9 10) (third-or-fifth [0 1 2 3 4 5 6 7 8 9 10])))

(deftest sqr-of-the-first-test
  (is (= '(16 16 16) (sqr-of-the-first '(4 5 6)))))

(deftest split-comb-test
  (are [x y] (= x y)
             '(1 3 2 4) (split-comb [1 2 3 4])
             '(1 3 2 4 5) (split-comb [1 2 3 4 5])))

(deftest muted-thirds-test
  (are [x y] (= x y)
             '(1 2 0 4 5 0) (muted-thirds [1 2 3 4 5 6])
             '() (muted-thirds [])))

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

(deftest distinct'-test
  (are [x y] (= x y)
             '(1 2 3) (distinct' '(1 1 2 2 3 3 3))
             '(1 2 3) (distinct' '(1 2 2 1 3 3 3))
             '() (distinct' '())))

(deftest dedupe'-test
  (are [x y] (= x y)
             '(1 2 3 4) (dedupe' [1 1 2 3 3 4])
             '(1 2 3 4 3) (dedupe' [1 1 2 3 4 3 3])))

(deftest sum-of-adjacent-digits-test
  (are [x y] (= x y)
             '(3 5) (sum-of-adjacent-digits [1 2 3])
             '(3 5 7) (sum-of-adjacent-digits [1 2 3 4])))

(deftest max-three-digit-sequence-test
  (testing "less than 3 elements"
    (is (= [1 2] (max-three-digit-sequence [1 2]))))
  (testing "three elements or more"
    (are [x y] (= x y)
               [4 5 6] (max-three-digit-sequence [1 2 4 5 6])
               [5 2 4] (max-three-digit-sequence [1 3 5 2 4 1]))))

(deftest russian-dolls-test
  (are [x y] (= x y)
             [[1] [2]] (russian-dolls [1 2] 2)
             [[[[1]]] [[[2]]]] (russian-dolls [1 2] 4))
  )


(deftest validate-sudoku-grid-test
  (testing "correct grid"
    (is (true? (validate-sudoku-grid
                 [[4 3 5 2 6 9 7 8 1]
                  [6 8 2 5 7 1 4 9 3]
                  [1 9 7 8 3 4 5 6 2]
                  [8 2 6 1 9 5 3 4 7]
                  [3 7 4 6 8 2 9 1 5]
                  [9 5 1 7 4 3 6 2 8]
                  [5 1 9 3 2 6 8 7 4]
                  [2 4 8 9 5 7 1 3 6]
                  [7 6 3 4 1 8 2 5 9]]))))
  (testing "incorrect grid"
    (is (false? (validate-sudoku-grid
                  [[4 4 5 2 6 9 7 8 1]
                   [6 8 2 5 7 1 4 9 3]
                   [1 9 7 8 3 4 5 6 2]
                   [8 2 6 1 9 5 3 4 7]
                   [3 7 4 6 8 2 9 1 5]
                   [9 5 1 7 4 3 6 2 8]
                   [5 1 9 3 2 6 8 7 4]
                   [2 4 8 9 5 7 1 3 6]
                   [7 6 3 4 1 8 2 5 9]])))))
