(ns clojure-miniprofiler.storages.in-memory
  (:require [clojure-miniprofiler.store :refer :all]))

(deftype InMemoryStore [store]
  Storage
  (save [_ profile]
    (swap! store assoc (get profile "Id") profile))
  (fetch [_ id]
    (get @store id)))

(defn in-memory-store
  "Creates an in-memory Miniprofiler results store."
  []
  (InMemoryStore. (atom {})))
