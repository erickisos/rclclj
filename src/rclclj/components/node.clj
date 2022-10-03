(ns rclclj.components.node
  (:require [clojure.string :as string]
            [rclclj.models.node :as models.node]
            [rclclj.protocols.node :as protocols.node]
            [schema.core :as s]))

(s/defrecord Node [name :- s/Str
                   config :- models.node/Config]
  protocols.node/INode
  (name! [_]
    name)
  (namespace! [_]
    (:namespace config))
  (fully-qualified-name! [this]
    (->> [(protocols.node/namespace! this) (protocols.node/name! this)]
         (string/join "/")
         (str "/")))
  (logger! [_]
    nil)
  (enable-rosout! [_ enable]
    ;; TODO: Check how to enable the rosout value without creating a new Node.
    nil))

(s/defn new-node :- protocols.node/INode
  ([name :- s/Str
    config]
   (->Node name config))
  ([name :- s/Str]
   (->Node name nil)))
