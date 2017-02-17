(defproject clojure-miniprofiler "0.5.0"
  :description "a simple but effective profiler for clojure web applications"
  :resource-paths ["resources/miniprofiler-ui"]
  :url "http://yellerapp.com/opensource/clojure-miniprofiler.html"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [cheshire "5.7.0"]
                 [ring/ring-core "1.5.1"]
                 [fipp "0.6.9"]]
  :profiles {:dev {:dependencies [[compojure "1.1.8"]]
                   :plugins [[lein-ring "0.8.11"]]
                   :ring {:handler clojure-miniprofiler.example/app}
                   :source-paths ["dev"]}
             :bench {:dependencies [[criterium "0.4.4"]
                                    [ring "1.5.1"]
                                    [compojure "1.5.2"]]
                     :main clojure-miniprofiler.bench
                     :source-paths ["src" "bench" "dev"]
                     :jvm-opts ^:replace
                     ["-Xms1024m" "-Xmx1024m" "-XX:+UseParNewGC" "-XX:+UseConcMarkSweepGC" "-server" "-XX:+CMSParallelRemarkEnabled" "-XX:+AggressiveOpts" "-XX:+UseFastAccessorMethods" "-Djava.awt.headless=true"]}})
