(ns rclclj.core
  (:require
   [rclclj.components.node :as components.node]
   [rclclj.models.node :as models.node]
   [rclclj.protocols.node :as protocols.node]
   [schema.core :as s]))

(s/defn init
  [args init-options]
  (let [context (global-context args init-options)]))

(s/defn create-node :- protocols.node/INode
  [name    :- s/Str
   context :- models.node/Config]
  (components.node/create-node name context))

(s/defn spin
  [node :- protocols.node/INode]
  (while (protocols.node/valid? node)
    (protocols.node/spin-once! node)))
