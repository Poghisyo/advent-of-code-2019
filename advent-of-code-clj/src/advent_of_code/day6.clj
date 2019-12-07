(ns advent-of-code.day6
  (:require [clojure.string :as string]))

(defn positions
  [pred coll]
  (keep-indexed (fn [idx x]
                  (when (pred x)
                    idx))
                coll))

(defn check-in-path
  [path curr-planet]
  (positions #(= curr-planet %) path))

(defn find-intersection-to-path
  [graph path orbit]
  (loop [acc []
         curr-planet (get graph orbit)]
    (if (not (empty? (check-in-path path curr-planet)))
      (+ (first (check-in-path path curr-planet)) (count (conj acc curr-planet)))
      (recur (conj acc curr-planet) (get graph curr-planet)))))

(defn map-path-to
  [graph target orbit]
  (loop [acc []
         curr-planet (get graph orbit)]
    (if (= target curr-planet)
      (conj acc curr-planet)
      (recur (conj acc curr-planet) (get graph curr-planet)))))

(defn count-links-to
  "take a planet and walk it back to center counting orbit-links"
  [graph target orbit]
  (loop [count 0
         curr-planet (get graph orbit)]
    (if (= target curr-planet)
      (inc count)
      (recur (inc count) (get graph curr-planet)))))

(defn count-all-links
  [graph]
  (reduce + (map #(count-links-to graph "COM" %) (keys graph))))

(defn parse-orbits
  "want graph of paths from planets to center (children -> parents)"
  [orbits]
  (let [graph (reduce (fn [acc orbit]
                        (let [[planet child] (string/split orbit #"\)")]
                          (assoc acc child planet)))
                      {}
                      orbits)]
    graph))

;
; (comment
;  (def orbits (string/split (slurp "/Users/willem/code/Poghisyo/advent-of-code-2019/advent-of-code-clj/resources/day6.txt") #"\n"))
;  (def all-orbits (parse-orbits orbits))
;  (def santas-path-to-center (map-path-to all-orbits "COM" "SAN"))
;  (def my-path-to-center (map-path-to all-orbits "COM" "YOU"))
;  (prn (check-in-path santas-path-to-center "XXX"))
;  (prn (find-intersection-to-path all-orbits santas-path-to-center "YOU")))

(defn -main
  [& args]
  (let [input (string/split (slurp "/Users/willem/code/Poghisyo/advent-of-code-2019/advent-of-code-clj/resources/day6.txt") #"\n")
        all-orbits (parse-orbits input)
        santas-path-to-center (map-path-to all-orbits "COM" "SAN")
        orbital-transitions-to-santa (- (find-intersection-to-path all-orbits santas-path-to-center "YOU") 1)]
    (prn (str "number of orbits: " (count-all-links all-orbits)))
    (prn (str "number of transitions to santa:" orbital-transitions-to-santa))))
