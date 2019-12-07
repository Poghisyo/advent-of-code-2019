(ns advent-of-code.day4
  (:require [clojure.string :as string]))


(defn split-number
  [number]
  (map #(Integer. %) (string/split (str number) #"")))

(defn keep-asc
  [number]
  (if (= number (sort number))
    number
    nil))

(defn keep-if-adjacent-repeat
  [number]
  (if (< (count (distinct number)) (count number))
    number
    nil))


(defn keep-with-only-double
  [number]
  (let [fr (frequencies number)
        s (reduce-kv (fn [acc v m] (if (= 2 m) (conj acc v) acc)) [] fr)]
    (if (empty? s)
      nil
      number)))


(defn run-assignment
  [range]
  (->> range
       (into [])
       (map #(split-number %))
       (map #(keep-asc %))
       (remove nil?)
       (map #(keep-with-only-double %))
       ; (map #(keep-if-adjacent-repeat %))
       (remove nil?)
       (prn #(count %))))

(defn -main
    [& args]
    (run-assignment (range 178416 676462)))
