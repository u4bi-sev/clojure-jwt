(defproject c-jwt "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [ring/ring-core "1.6.3"]
                 [ring/ring-jetty-adapter "1.6.3"]
                 [compojure "1.6.1"]
                 [ring/ring-json "0.4.0"]
                 [ring-cors "0.1.12"]]
  :plugins [[lein-ring "0.12.4"]]
  :ring {:handler c-jwt.core/app})