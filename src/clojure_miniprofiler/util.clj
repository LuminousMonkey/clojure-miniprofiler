(ns clojure-miniprofiler.util)

(defn uuid
  "generates an uuid"
  []
  (str (java.util.UUID/randomUUID)))

(defn distance-of-ns-time [ns0 ns1]
  (/ (float (- ns1 ns0)) 1000000))

(defn current-ms []
  (System/currentTimeMillis))
