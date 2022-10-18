(ns rclclj.components.node
  (:require
   [clojure.string :as string]
   [rclclj.protocols.context :as protocols.context]
   [rclclj.protocols.node :as protocols.node]
   [schema.core :as s]))

(s/defrecord Node [name    :- s/Str
                   context :- protocols.context/IContext]
  protocols.node/INode
  (name! [_]
    name)
  (namespace! [_]
    (:namespace context))
  (fully-qualified-name! [this]
    (->> (protocols.node/name! this)
         (vector (protocols.node/namespace! this))
         (string/join "/")
         (str "/")))
  (logger! [_]
    ;; TODO: Check how to answer with a logger for this
    nil)
  (valid? [_]
    (protocols.context/valid? context))
  (spin! [this]
    (while (protocols.node/valid? this)
      (protocols.node/spin-once! this)))
  (spin-once! [_]
    nil)
  (rosout? [_ enable]
    ;; TODO: Check how to enable the rosout value without creating a new Node.
    enable))

(s/defn create-node :- protocols.node/INode
  ([name    :- s/Str
    context :- protocols.context/IContext]
   (->Node name context))
  ([name :- s/Str]
   (->Node name nil)))
