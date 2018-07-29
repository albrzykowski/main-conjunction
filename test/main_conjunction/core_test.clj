(ns main-conjunction.core-test
  (:require [clojure.test :refer :all]
            [main-conjunction.core :refer :all]
   :use [clojure.string :only [split]]))

(deftest calc-els-vals-test
  (testing "If conjunctions values are returned"
    (def formula (split "( ( NOT ( p OR q ) OR q ) AND ( p AND NOT r ) )" #" "))
    (def result [0 1 1 2 3 2 3 2 1 2 1 0 1 2 1 1 2 1 0])
    (is (= (calc-els-vals formula) result))))

(deftest calc-els-vals-exception-test
  (testing "If exception is thrown when formula contains illegal elements"
    (def formula (split "( + ( p OR q ) )" #" "))
    (is (thrown? IllegalArgumentException (calc-els-vals formula))))) 

(deftest main-conj-pos-test
  (testing "If main conjuctions positions are returned"
    (def formula (split "( ( NOT ( p OR q ) OR q ) AND ( p AND NOT r ) )" #" "))
    (def result '(0 11 18))
    (is (= (main-conj-pos (calc-els-vals formula)) result))))     
    
