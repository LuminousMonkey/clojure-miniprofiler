(ns clojure-miniprofiler.types
  (:require [cheshire.generate :as json-generate]))

(defprotocol ToMiniProfilerMap
  (to-miniprofiler-map [this]))

(defrecord Profiler [current-timer root start-ns])
(defrecord Profile [id profile-name started duration-ms machine-name root client-timings]
  ToMiniProfilerMap
  (to-miniprofiler-map [_]
    {"Id" id
     "Name" profile-name
     "Started" started
     "DurationMilliseconds" duration-ms
     "MachineName" machine-name
     "Root" (to-miniprofiler-map root)
     "ClientTimings" client-timings}))

(json-generate/add-encoder
  Profile
  (fn [p g]
    (json-generate/encode-map
      (to-miniprofiler-map p)
      g)))

(defrecord Options [base-path authorized? trivial-ms initial-opts])
