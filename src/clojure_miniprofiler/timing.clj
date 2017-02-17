(ns clojure-miniprofiler.timing
  (:require [clojure-miniprofiler.types :refer
             [ToMiniProfilerMap to-miniprofiler-map]]
            [cheshire.generate :as json-generate]))

(defrecord Timing [id timing-name start-ms duration-ms children custom-timings]
  ToMiniProfilerMap
  (to-miniprofiler-map [_]
    {"Id" id
     "Name" timing-name
     "StartMilliseconds" start-ms
     "DurationMilliseconds" duration-ms
     "Children" (map to-miniprofiler-map children)
     "CustomTimings" (into {}
                           (map (fn [[k v]]
                                  [k (map to-miniprofiler-map v)]) custom-timings))}))

(defrecord CustomTiming [id execute-type command-string stacktrace-snippet start-ms duration-ms]
  ToMiniProfilerMap
  (to-miniprofiler-map [_]
    {"Id" id
     "ExecuteType" execute-type
     "CommandString" command-string
     "StackTraceSnippet" stacktrace-snippet
     "StartMilliseconds" start-ms
     "DurationMilliseconds" duration-ms}))

(json-generate/add-encoder
  Timing
  (fn [t g]
    (json-generate/encode-map
      (to-miniprofiler-map t)
      g)))

(json-generate/add-encoder
  CustomTiming
  (fn [t g]
    (json-generate/encode-map
      (to-miniprofiler-map t)
      g)))
