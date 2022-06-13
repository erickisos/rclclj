(ns rclclj.components.node
  (:require [schema.core :as s]
            [rclclj.protocols.node :as protocols.node]))

(s/defrecord Node [name :- s/Str
                   config]
  protocols.node/INode)

(s/defn new-node :- protocols.node/INode
  [name :- s/Str
   config]
  (->Node name config))
