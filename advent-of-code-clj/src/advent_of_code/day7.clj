(ns advent-of-code.day7
  (:require [advent-of-code.day5 :as intcode])
  (refer advent-of-code.day5 :only [run-intcode]))

(def puz-input [
                3,8,1001,8,10,8,105,1,0,0,21,42,59,76,85,106,187,268,349,430,99999,3,9,102,3,9,9,1001,9,2,9,1002,9,3,9,1001,9,3,9,4,9,99,3,9,102,3,9,9,101,3,9,9,1002,9,2,9,4,9,99,3,9,102,3,9,9,1001,9,4,9,1002,9,5,9,4,9,99,3,9,102,2,9,9,4,9,99,3,9,101,3,9,9,1002,9,2,9,1001,9,4,9,1002,9,2,9,4,9,99,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,1,9,9,4,9,3,9,101,1,9,9,4,9,3,9,102,2,9,9,4,9,3,9,101,2,9,9,4,9,99,3,9,1002,9,2,9,4,9,3,9,101,1,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,101,1,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,1,9,9,4,9,3,9,101,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,101,2,9,9,4,9,99,3,9,1001,9,2,9,4,9,3,9,101,1,9,9,4,9,3,9,102,2,9,9,4,9,3,9,101,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,101,2,9,9,4,9,3,9,101,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,1,9,4,9,3,9,102,2,9,9,4,9,99,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,101,1,9,9,4,9,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,101,1,9,9,4,9,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,99,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,101,1,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,99])

(defn run-amp
  "take setting and input - run intcode - output res"
  [input setting]
  (let [result (intcode/run-intcode input setting)]
    result))

(comment
  (def test-1-input [3,15,3,16,1002,16,10,16,1,16,15,15,4,15,99,0,0])
  (def test-2-input [3,23,3,24,1002,24,10,24,1002,23,-1,23,101,5,23,23,1,24,23,23,4,23,99,0,0])
  (def test-3-input [3,31,3,32,1002,32,10,32,1001,31,-2,31,1007,31,0,33,1002,33,7,33,1,33,31,31,1,32,31,31,4,31,99,0,0,0])
  (def test-1-settings [4,3,2,1,0])
  (def test-1-settings [0,1,2,3,4])
  (def test-1-settings [1,0,4,3,2])
  (let [result (run-amp test-3-input 1)
        res-1 (run-amp result 0)
        res-2 (run-amp res-1 4)
        res-3 (run-amp res-2 3)
        res-4 (run-amp res-3 2)]
   (clojure.pprint/pprint  "x")
   (println res-4)))
(defn run-thru-amps
  [input settings]
  (let [[phase-1 phase-2 phase-3 phase-4 phase-5] (take 5 settings)
        result (run-amp input [phase-1 0])
        res-1 (run-amp (first result) [phase-2 (last (second result))])
        res-2 (run-amp (first res-1) [phase-3 (last (second res-1))])
        res-3 (run-amp (first res-2) [phase-4 (last (second res-2))])
        res-4 (run-amp (first res-3) [phase-5 (last (second res-3))])]
    (first (last res-4))))

(defn get-phases
  [a b]
  (let [all (for [v (range a b)
                  w (range a b)
                  x (range a b)
                  y (range a b)
                  z (range a b)]
              [v w x y z])

        valid (reduce (fn [acc v]
                        (if (= 5 (count (distinct v)))
                          (conj acc v)
                          acc))
                      []
                      all)]
    valid))


(defn aco
  "amplifier controller"
  [input]
  (let [phases (get-phases 0 5)
        outputs (reduce (fn [acc v]
                           (conj acc (run-thru-amps input v)))
                        []
                        phases)]
    (first (last outputs))))


(defn -main
  [& args]
  (println (str "prob 1: " (aco puz-input)))
  (println (time (aco puz-input))))


; ==============================
; Future
; A Future is similar to a Promise. The main difference is that Futures will evaluate an expression for you in another Thread. You don't have to set the thread up. Here's how the same thing works using future.
;
; (def the-answer (future
;                    ;; do a lot of work in a new thread
;                    ;; ...
;                    ;; then deliver the answer
;                    ;; the value of the last expression is delivered
;                    42))
;
; ;; in the main thread, do some work
; ;; ...
; ;; then get the answer (which blocks until the answer is done)
; (println (deref the-answer))))

; One thing that trips up people new to Futures is that they swallow exceptions. If the code you run in the Future throws an exception, you won't hear about it until you deref it. When you deref it, the exception will be thrown again in the current thread.
