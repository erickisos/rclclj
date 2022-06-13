(ns unit.rclclj.components.node-test
  (:require [schema.test :as st]
            [rclclj.protocols.node :as protocols.node]
            [clojure.test :refer [testing is]]
            [matcher-combinators.test :refer [match?]]
            [rclclj.components.node :as components.node]))

(def mock-node (components.node/new-node "mock" {:namespace "test"}))

(st/deftest node-tests
  (testing "that we can get the node name"
    (is (match? "mock"
                (protocols.node/name! mock-node))))
  (testing "that we can get the namespace of the node"
    (is (match? "test"
                (protocols.node/namespace! mock-node))))
  (testing "that the mock has a fully qualified name"
    (is (match? "/test/mock"
                (protocols.node/fully-qualified-name! mock-node)))))
