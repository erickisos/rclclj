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
                   enable-rosout?  :- s/Bool
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
    )
  (rosout? [_]
    enable-rosout?))

(s/defn create-node :- protocols.node/INode
  ([name :- s/Str
    context     :- protocols.context/IContext
    enable-rosout? :- s/Bool
    clients     :- [s/Any]
    services    :- [s/Any]
    subscriptions :- [s/Any]]
   (->Node name context enable-rosout? clients services subscriptions))
  ([name :- s/Str
    context     :- protocols.context/IContext
    enable-rosout? :- s/Bool
    clients     :- [s/Any]
    services    :- [s/Any]]
   (create-node name context enable-rosout? clients services []))
  ([name :- s/Str
    context     :- protocols.context/IContext
    enable-rosout? :- s/Bool
    clients     :- [s/Any]]
   (create-node name context enable-rosout? clients []))
  ([name :- s/Str
    context     :- protocols.context/IContext
    enable-rosout? :- s/Bool]
   (create-node name context enable-rosout? [])
  ([name    :- s/Str
    context :- protocols.context/IContext]
   (create-node name context true))
  ([name :- s/Str]
   (create-node name nil)))
