(ns rclclj.components.node
  (:require
   [clojure.string :as string]
   [rclclj.protocols.context :as protocols.context]
   [rclclj.protocols.node :as protocols.node]
   [schema.core :as s]))

(s/defn sanitize! :- (s/maybe s/Str)
  [name :- (s/maybe s/Str)]
  (when name
    (string/replace name #"[^a-zA-Z0-9_]" "_")))

(s/defrecord Node [name    :- s/Str
                   context :- protocols.context/IContext
                   rosout  :- s/Bool
                   clients :- []
                   services :- []
                   subscriptions :- []]
  protocols.node/INode
  (name! [_]
    (sanitize! name))
  (namespace! [_]
    (sanitize! (:namespace context)))
  (fully-qualified-name! [this]
    (->> (protocols.node/name! this)
         (vector (protocols.node/namespace! this))
         (filter not-empty)
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
  ([name :- s/Str
    context :- protocols.context/IContext
    rosout :- s/Bool]
   (->Node name context rosout))
  ([name    :- s/Str
    context :- protocols.context/IContext]
   (->Node name context))
  ([name :- s/Str]
   (->Node name nil)))
