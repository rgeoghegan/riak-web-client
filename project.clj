(defproject riak-client "1.0.0-SNAPSHOT"
  :description "Cheap gui client for riak"
  :dev-dependencies [[uk.org.alienscience/leiningen-war "0.0.13"]]
  :dependencies [[org.clojure/clojure "1.2.0"]
                 [org.clojure/clojure-contrib "1.2.0"]
                 [clj-riak "0.1.0-SNAPSHOT"]
                 [javax.servlet/servlet-api "2.5"]
                 ]
  :aot [riak-client.servlet]
  :war {
        :name "deploy/ROOT.war"
        }
  )
