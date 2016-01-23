(defproject tala-api-new "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [compojure "1.4.0"]
                 [ring-server "0.4.0"]
                 [ring/ring-core "1.4.0"]
                 [ring/ring-json "0.4.0"]
                 [ring/ring-servlet "1.1.0"]
                 [amalloy/ring-gzip-middleware "0.1.3"]]
  :aot [tala.core]
  :profiles {:dev        {:dependencies [[speclj "3.3.1"]]}
             :production {:ring {:open-browser? false, :stacktraces? true, :auto-reload? false}}}
  :plugins [[speclj "3.3.1"]
            [lein-ring "0.7.1"]]
  :test-paths ["spec"]
  :ring {:handler tala.core/app})
