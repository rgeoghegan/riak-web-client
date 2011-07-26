# riak-web-client

Web app to browse a Riak database.

## Usage

If you are on mac os x, get homebrew, and use it to install clojure and jetty. Type the following to get the dependencies:

    lein deps

Then type the following to create the deplot/ROOT.war file:

    lein uberwar

Use the run.sh and the web-app.clj script to run the web-app using Jetty and Homebrew. Otherwise, serve up the war file
any way you see fit.

## License

Copyright (C) 2011 Rory Geoghegan

Distributed under the Eclipse Public License, the same as Clojure.
