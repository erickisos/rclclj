(ns unit.rclclj.components.core-test
  (:require
   [clojure.test :refer [is testing]]
   [matcher-combinators.test :refer [match?]]
   [schema.test :as st]))

(st/deftest core-tests
  (testing ""
    (is (match? nil
                true))))
