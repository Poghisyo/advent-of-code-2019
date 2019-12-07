(ns advent-of-code.day6
  (:require [clojure.string :as string]))


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

(defn -main
  [& args]
  (let [input (string/split (slurp "/Users/willem/code/Poghisyo/advent-of-code-2019/advent-of-code-clj/resources/day6.txt") #"\n")
        all-orbits (parse-orbits input)]
    (prn (str "number of orbits: " (count-all-links all-orbits)))))
