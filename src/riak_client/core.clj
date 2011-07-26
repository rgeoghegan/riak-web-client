(ns riak-client.core
  (:require [clj-riak.client :as client])
  (:use riak-client.gui)
  (:gen-class)
  )

(defn -main [& args]
  (let [
        host (if (< 0 (count args))
               (first args)
               "localhost")
        port (if (< 1 (count args))
               (Integer/parseInt (second args))
               8081)
        rc (client/init {:host host :port port})
        ]
    (start-client rc))
  )



