(ns web-app
  (:import [org.mortbay.jetty Server])
  (:import org.mortbay.jetty.webapp.WebAppContext)
  (:require clojure.contrib.io)
  )

(def WAR_PATH
  (str
   (clojure.contrib.io/pwd)
   "/deploy/ROOT.war"))

(let [
      webapp (doto
                 (WebAppContext.)
               (.setContextPath "/")
               (.setWar WAR_PATH)
               )
      ]
  (doto
      (Server. 4567)
    (.setHandler webapp)
    (.start)
    (.join)
  ))
