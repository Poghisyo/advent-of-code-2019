(ns advent-of-code.day5
  (:require []))

(def input [1,12,2,3,1,1,2,3,1,3,4,3,1,5,0,3,2,1,6,19,1,9,19,23,2,23,10,27,1,27,5,31,1,31,6,35,1,6,35,39,2,39,13,43,1,9,43,47,2,9,47,51,1,51,6,55,2,55,10,59,1,59,5,63,2,10,63,67,2,9,67,71,1,71,5,75,2,10,75,79,1,79,6,83,2,10,83,87,1,5,87,91,2,9,91,95,1,95,5,99,1,99,2,103,1,103,13,0,99,2,14,0,0])

(defn run-intcode
  [input]
  (loop [position 0
         input input]
    (let [opcode (get input position)
          end (or (= 99 opcode) (>= (+ 4 position) (count input)))
          noun (get input (get input (+ 1 position)))
          verb (get input (get input (+ 2 position)))
          output (case opcode
                   1 (+ noun verb)
                   2 (* noun verb)
                   99 nil
                   :else nil)
          target (get input (+ 3 position))
          result (if output
                   (reduce-kv (fn [m k v]

                               (if (= k target)
                                 (conj m output)
                                 (conj m v)))

                           []
                           input)
                   input)]
      (if end
        (first result)
        (recur (+ 4 position) result)))))

(defn run-prog
  [input position]
  (doseq [x (vec (range 0 100))
          y (vec (range 0 100))]
    (let [ninput (vec (concat [(first input)] [x y] (rest (rest (rest input)))))
          result (run-intcode ninput)]
      (if (= result 19690720)
        (prn [x y])))))

(comment
  (run-prog input 0)
  (prn (run-intcode input)))
