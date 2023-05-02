(ns unit.rclclj.components.node-test
  (:require
   [clojure.test :refer [is testing]]
   [matcher-combinators.test :refer [match?]]
   [rclclj.components.node :as components.node]
   [rclclj.protocols.node :as protocols.node]
   [schema.test :as st]))

(def mock-node (components.node/create-node "mock" {:namespace "test"}))
(def mock-node-02 (components.node/create-node "mock-valid" {:namespace "another-test"}))
(def mock-node-03 (components.node/create-node "mock-wo-namespace"))

(st/deftest node-tests
  (testing "that we can get the node name"
    (is (match? "mock"
                (protocols.node/name! mock-node))))
  (testing "that we can get the namespace of the node"
    (is (match? "test"
                (protocols.node/namespace! mock-node))))
  (testing "that the mock has a fully qualified name"
    (is (match? "/test/mock"
                (protocols.node/fully-qualified-name! mock-node))))
  (testing "that we should automatically parse the namespace to a valid one"
    (is (match? "/another_test/mock_valid"
                (protocols.node/fully-qualified-name! mock-node-02))))
  (testing "that we can parse a node without namespace"
    (is (match? "/mock_wo_namespace"
                (protocols.node/fully-qualified-name! mock-node-03))))
  (testing "that we don't receive a failure with the lack of namespace"
    (is (nil? (protocols.node/namespace! mock-node-03)))))
