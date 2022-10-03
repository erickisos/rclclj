(ns rclclj.components.node
  (:require [schema.core :as s]
            [rclclj.protocols.node :as protocols.node]
            [clojure.string :as string]))

(s/defrecord Node [name :- s/Str
                   config]
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
  (enable_rosout! [_ _enable]
    nil))

(s/defn new-node :- protocols.node/INode
  ([name :- s/Str
    config]
   (->Node name config))
  ([name :- s/Str]
   (->Node name nil)))
