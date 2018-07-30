(ns main-conjunction.core
  (:use [clojure.string :only [split lower-case]] :reload))

(defn- calculate-element-value [current previous]
  (cond
    (and (nil? previous) (= current "(")) 0
    (or (contains? #{"("} current) (re-matches #"[a-z]+" current)) (+ previous 1)
    (contains? #{")" "OR" "AND" "IMPL" "IFF"} current) (- previous 1)
    (contains? #{"NOT"} current) previous
    :else (throw (IllegalArgumentException.))))

(defn calc-els-vals [formula]
  (loop [acc [] f formula]
    (def current (first f))
    (def previous (last acc))
    (if (nil? current)
      acc
      (recur (conj acc (calculate-element-value current previous)) (rest f)))))

(defn main-conj-pos [els-vals]
  (map first 
    (filter #(= (second %) 0)
      (map-indexed vector els-vals))))

(defn -main [& args]
  (def fo (split "( p AND ( q OR r ) )" #" "))
  (println (calc-els-vals fo)))      
