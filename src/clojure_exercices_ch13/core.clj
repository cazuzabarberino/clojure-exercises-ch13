(ns clojure-exercices-ch13.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

(defmulti full-moon-behavior (fn [were-creature] (:were-type were-creature)))

(defmethod full-moon-behavior :wolf
  [were-creature]
  (str (:name were-creature) " will howl and murder"))

(defmethod full-moon-behavior :simmons
  [were-creature]
  (str (:name were-creature) " will encourage people and sweat to the oldies"))

(full-moon-behavior {:were-type :wolf
                     :name "Rachel from next door"})

(full-moon-behavior {:name "Andy the baker"
                     :were-type :simmons})

(defmethod full-moon-behavior :programmer
  [were-creature]
  (str (:name were-creature) " is programmer"))

(full-moon-behavior {:name "Madmax"
                     :were-type :programmer})


(defprotocol WereCreature
  (full-moon-behavior-2 [x]))

(defrecord WereSimmons [name title]
  WereCreature
  (full-moon-behavior-2 [x]
    (str (:name x) " will encourage people and sweat to the oldies")))

(full-moon-behavior-2 (WereSimmons. "Simmons" "lorem title"))


(defprotocol MyProtocol
  (say-hello [x]))

(extend-type java.lang.Long MyProtocol
             (say-hello [num] (str "Hello number " num)))

(say-hello 4)

(extend-protocol MyProtocol
  String
  (say-hello [s] (str "Hello " s))
  clojure.lang.PersistentVector
  (say-hello [v] (str "Hello vector" v)))

(say-hello "won't fail")
(say-hello [1 2 3])