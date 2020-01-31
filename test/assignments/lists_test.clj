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
