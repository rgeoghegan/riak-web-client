(ns riak-client.servlet
  (:use clojure.contrib.prxml)
  (:require [clojure.contrib.json :as json]
            [clj-riak.client :as client])
  )

(defn prxml-to-out
  "Uses the prxml library to create html which is written to out."
  [out & struct]
  (with-bindings
    {#'*out* out
     #'*prxml-indent* 4
     #'*html-compatible* true}
    (apply prxml struct))
  )

(gen-class
 :name riak-client.servlet.ListBuckets
 :extends javax.servlet.http.HttpServlet
 :prefix "list-buckets-"
  )

(defn list-buckets-doGet [this request response]
  (let [
        host (.getParameter request "host")
        port (Integer/parseInt (.getParameter request "port"))
        rc (client/init {:host host :port port})
        ]
  (.write
   (.getWriter response)
   (json/json-str (client/list-buckets rc))
   )
  ))

(gen-class
 :name riak-client.servlet.ListKeys
 :extends javax.servlet.http.HttpServlet
 :prefix "list-keys-"
 )

(defn list-keys-doGet [this request response]
  (let [
        host (.getParameter request "host")
        port (Integer/parseInt (.getParameter request "port"))
        bucket (.getParameter request "bucket")
        rc (client/init {:host host :port port})
        ]
  (.write
   (.getWriter response)
   (json/json-str (client/list-keys rc bucket))
   )
  ))

(gen-class
 :name riak-client.servlet.ShowValue
 :extends javax.servlet.http.HttpServlet
 :prefix "show-value-"
 )

(defn show-value-doGet [this request response]
  (let [
        host (.getParameter request "host")
        port (Integer/parseInt (.getParameter request "port"))
        bucket (.getParameter request "bucket")
        key (.getParameter request "key")
        rc (client/init {:host host :port port})
        value (String. ((client/get rc bucket key) :value))
        ]
    (.write
     (.getWriter response)
     (json/json-str {:value value}))
  ))
