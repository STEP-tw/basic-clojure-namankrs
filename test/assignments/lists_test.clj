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
