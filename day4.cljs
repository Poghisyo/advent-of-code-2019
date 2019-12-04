(ns day_3
  (:require [])


  (defn split-number
    [number]
    (map #(Integer. %) (clojure.string/split (str number) #"")))

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
         ;(map #(keep-if-adjacent-repeat %))
         (remove nil?)
         (count)))

  (comment
    (run-assignment (range 178416 676462))
    (run-assignment (range 11 2222))))
