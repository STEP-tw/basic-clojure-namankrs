(ns assignments.lists)

(defn all-first [colls]
  (loop [collections colls result '()]
        (if (empty? collections)
            result
            (recur (rest collections) (concat result [(ffirst collections)])))))

(defn all-rest [colls]
  (loop [collections colls result '()]
        (if (empty? collections)
            result
            (recur (rest collections) (concat result [(rest (first collections))])))))

(defn map'
  "Implement a non-lazy version of map that accepts a
  mapper function and several collections. The output
  should be consistent with clojure.core/map"
  {:level        :medium
   :use          '[loop recur]
   :dont-use     '[map]
   :implemented? true}
  [f & colls] (loop [collections colls result '()]
                    (if (some empty? collections)
                        result
                        (recur (all-rest collections)
                               (concat result [(apply f (all-first collections))])))))

(defn filter'
  "Implement a non-lazy version of filter that accepts a
  predicate function and a collection. The output
  should be consistent with clojure.core/filter"
  {:level        :easy
   :use          '[loop recur]
   :dont-use     '[filter]
   :implemented? true}
  [pred coll] (loop [coll coll result '()]
                    (if (empty? coll)
                        result
                        (recur (rest coll)
                               (if (true? (pred (first coll))) (concat result [(first coll)]) result)))))

(defn reduce'
  "Implement your own multi-arity version of reduce
  that accepts a predicate function and a collection.
  The output should be consistent with clojure.core/reduce"
  {:level        :medium
   :use          '[loop recur]
   :dont-use     '[reduce]
   :implemented? true}
  ([f coll] (loop [collection (drop 2 coll) result (f (first coll) (second coll))]
                  (if (empty? collection)
                      result
                      (recur (rest collection) (f result (first collection))))))
  ([f init coll] (reduce' f (cons init coll))))

(defn count'
  "Implement your own version of count that counts the
  number of elements in a given sequence"
  {:level        :easy
   :use          '[loop recur]
   :dont-use     '[count]
   :implemented? true}
  ([coll] (loop [coll coll count 0]
                (if (empty? coll)
                    count
                    (recur (rest coll) (inc count))))))

(defn reverse'
  "Implement your own version of reverse that reverses a coll.
  Returns nil if coll provided is not a sequence"
  {:level        :easy
   :use          '[reduce conj seqable? when]
   :dont-use     '[reverse]
   :implemented? true}
  ([coll] (when (seqable? coll)
            (reduce conj '() coll))))

(defn every?'
  "Implement your own version of every? that checks if every
  element of a coll satisfies the given predicate"
  {:level        :easy
   :use          '[loop recur and]
   :dont-use     '[every?]
   :implemented? true}
  ([pred coll] (loop [coll coll result true]
                     (if (empty? coll)
                         result
                         (recur (rest coll) (and result (pred (first coll))))))))

(defn some?'
  "Implement your own version of some that checks if at least one
  element of a coll satisfies the given predicate. Always return
  a boolean. The original clojure.core/some returns a nil when
  no element satisfies the given predicate"
  {:level        :easy
   :use          '[loop recur or]
   :dont-use     '[some]
   :implemented? true}
  ([pred coll] (loop [coll coll result false]
                     (if (empty? coll)
                         result
                         (recur (rest coll) (or result (pred (first coll))))))))

(defn ascending?
  "Verify if every element is greater than or equal to its predecessor"
  {:level        :easy
   :use          '[partition every? partial apply <=]
   :dont-use     '[loop recur]
   :implemented? true}
  [coll] (every?' (partial apply <=) (partition 2 coll)))

(defn distinct'
  "Implement your own lazy sequence version of distinct which returns
  a collection with duplicates eliminated. Might have to implement another
  function, or use a letfn"
  {:level        :medium
   :use          '[lazy-seq set conj let :optionally letfn]
   :dont-use     '[loop recur distinct]
   :implemented? true}
  [coll] (lazy-seq
           (reduce #(if (some (partial = %2) %1) %1 (concat %1 [%2])) '() coll)))

(defn dedupe'
  "Implement your own lazy sequence version of dedupe which returns
  a collection with consecutive duplicates eliminated (like the uniq command).
  Might have to implement another function, or use a letfn"
  {:level        :medium
   :use          '[lazy-seq conj let :optionally letfn]
   :dont-use     '[loop recur dedupe]
   :implemented? true}
  [coll] (lazy-seq (map first (partition-by identity coll))))

(defn sum-of-adjacent-digits
  "Given a collection, returns a map of the sum of adjacent digits.
  [a b c] => [a+b b+c]"
  {:level        :medium
   :use          '[map + rest]
   :dont-use     '[loop recur partition]
   :implemented? true}
  [coll] (map + coll (rest coll)))

(defn max-three-digit-sequence
  "Given a collection of numbers, find a three digit sequence that
  yields the max sum. If the collection has fewer than 3 elements,
  returns the collection itself.
  [1 2 -1 2 0] => [2 -1 2]"
  {:level        :medium
   :use          '[map next nnext max-key partial apply + if ->>]
   :dont-use     '[loop recur partition]
   :implemented? true}
  [coll] (if (< (count coll) 3)
             coll
             (apply max-key (partial apply +) (map vector coll (next coll) (nnext coll)))))

;; transpose is a def. Not a defn.
(def
  ^{:level        :easy
    :dont-use     '[loop recur for nth get]
    :implemented? true}
  transpose
  "Transposes a given matrix.
  [[a b] [c d]] => [[a c] [b d]].
  Note this is a def. Not a defn.
  Return a vector of vectors, not list of vectors or vectors of lists"
  (fn [matrix] (apply mapv vector matrix)))

(defn difference
  "Given two collections, returns only the elements that are present
  in the second coll but not the first"
  {:level        :easy
   :use          '[remove set]
   :dont-use     '[loop recur if]
   :implemented? true}
  [coll1 coll2] (remove (set coll1) (set coll2)))

(defn union
  "Given two collections, returns a new collection with elements from the second
  collection added to the first collection if they are missing in the first
  collection to begin with. Return a list, not a set. It also doesn't matter
  if elements repeat."
  {:level        :easy
   :use          '[remove into set ->>]
   :implemented? true}
  [coll1 coll2] (->> coll2
                     (remove (set coll1))
                     (into coll1)))

;; points-around-origin is a def not a defn
(def
  ^{:level        :easy
    :use          '[for]
    :dont-use     '[hardcoded-values map filter]
    :implemented? true}
  points-around-origin
  "Calculate all the points around the origin
  [-1 -1] [0 -1] [1 -1] etc. There should be 8 points
  Note this is a def, not a defn"
  (fn [] (for [x '(0 -1 1) y '(0 -1 1) :when (not= x y 0)] [x y])))


(defn cross-product
  "Given two sequences, generate every combination in the sequence
  until two elements are equal
  [1 2 3] [4 3 5] =>
  [[1 4] [1 3] [1 5] [2 4] [2 3] [2 5] [3 4]]"
  {:level        :easy
   :use          '[for]
   :implemented? true}
  [seq1 seq2] (for [x seq1 y seq2 :while (not= x y)] [x y]))

(defn double-up
  "Given a collection, return a new collection that contains
  each element repeated twice"
  {:level        :easy
   :use          '[mapcat partial repeat :optionally vector]
   :implemented? true}
  [coll] (mapcat (partial repeat 2) coll))

(defn third-or-fifth
  "Given a collection return a new collection that contains
  elements whose index is either divisible by three or five"
  {:level        :easy
   :use          '[keep-indexed when :optionally map-indexed filter]
   :implemented? true}
  [coll] (keep-indexed #(if (or (zero? (mod %1 3)) (zero? (mod %1 5))) %2) coll))

(defn sqr-of-the-first
  "Given a collection, return a new collection that contains the
  same number of elements as the given collection all of which
  are the square of the first element
  [4 5 6] => [16 16 16]"
  {:level        :easy
   :use          '[map constantly let]
   :implemented? true}
  [coll] (map (constantly (* (first coll) (first coll))) coll))

(defn russian-dolls
  "Given a collection and a number, wrap each element in a nested vector
  with a nesting factor of the number provided.
  [1 2 3] 3 => [[[1]] [[2]] [[3]]]"
  {:level        :medium
   :use          '[iterate mapv partial vector drop first ->>]
   :dont-use     '[for loop recur reduce]
   :implemented? true}
  [coll nesting-factor] (->> coll
                             (iterate (partial mapv vector))
                             (drop (dec nesting-factor))
                             first))

(defn interleave-even [coll]
  (apply interleave (split-at (int (/ (count coll) 2)) coll)))

(defn split-comb
  "Given a collection, return a new sequence where the first
  half of the sequence is interleaved with the second half.
  If the given collection has an odd number of elements, then
  preserve the last element of the original collection
  [1 2 3 4 5] => [1 3 2 4 5]"
  {:level        :easy
   :use          '[interleave split-at if rem concat take-last]
   :dont-use     '[loop recur map-indexed take drop]
   :implemented? true}
  [coll] (if (even? (count coll))
             (interleave-even coll)
             (concat (interleave-even coll) (take-last 1 coll))))

(defn muted-thirds
  "Given a sequence of numbers, make every third element
  0 while preserving the other elements
  [1 2 8 4 15 2 7] => [1 2 0 4 15 0 7]"
  {:level        :easy
   :use          '[map cycle]
   :dont-use     '[loop recur map-indexed take take-nth]
   :implemented? true}
  [coll] (map * (cycle '(1 1 0)) coll))

(defn palindrome?
  "Implement a recursive palindrome check of any given sequence"
  {:level        :easy
   :use          '[empty? loop recur butlast rest]
   :dont-use     '[reverse]
   :implemented? true}
  [coll] (loop [coll coll result true]
               (if (empty? coll)
                   result
                   (recur (rest (butlast coll)) (and result (= (first coll) (last coll)))))))

(defn index-of
  "index-of takes a sequence and an element and finds the index
  of the element in the given sequence. Returns -1 if element
  is not found"
  {:level        :easy
   :use          '[loop recur rest]
   :dont-use     '[.indexOf memfn]
   :implemented? true}
  [coll n] (loop [coll coll curr-index 0 match-index -1]
                 (if (or (empty? coll) (not= match-index -1))
                     match-index
                     (recur (rest coll) (inc curr-index) (if (= (first coll) n) curr-index -1)))))

(defn valid-sudoku-row?
  [row]
  (= (set (range 1 10)) (set row)))

(defn get-sudoku-grids
  [board]
  (->> board
       (map (partial partition 3))
       (partition 3)
       (map (partial apply map list))
       (mapcat identity)
       (map flatten)))

(defn validate-sudoku-grid
  "Given a 9 by 9 sudoku grid, validate it."
  {:level        :hard
   :implemented? true}
  [grid]
  (every?' valid-sudoku-row?
           (concat grid (transpose grid) (get-sudoku-grids grid))))