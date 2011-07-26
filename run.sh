#!/usr/bin/env bash

cp=$CLASSPATH:.:$(brew --cellar)/jetty/6.1.26/libexec/lib/jetty-6.1.26.jar:\
$(brew --cellar)/jetty/6.1.26/libexec/lib/jetty-util-6.1.26.jar:\
$(brew --cellar)/jetty/6.1.26/libexec/lib/servlet-api-2.5-20081211.jar:\
$(brew --cellar)/clojure/1.2.0/clojure.jar:\
$(brew --cellar)/clojure-contrib/1.2.0/clojure-contrib.jar

java -cp $cp clojure.main "$@"
