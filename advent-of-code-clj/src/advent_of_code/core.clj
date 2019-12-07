(ns advent-of-code.core
  (:require [advent-of-code.day2]
            [advent-of-code.day3]
            [advent-of-code.day4]
            [advent-of-code.day5]))


(defn -main
  [& args]

  (println "hi ...\nWhich day to run [2 - 5]?\n>")
  (let [ans (read-line)]
    (case ans
      "1" nil
      "2" (advent-of-code.day2/-main)
      "3" (advent-of-code.day3/-main)
      "4" (advent-of-code.day4/-main)
      "5" (advent-of-code.day5/-main))))
