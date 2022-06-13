(ns rclclj.components.context
  (:require [rclclj.protocols.context :as protocols.context]
            [schema.core :as s]))

(s/defrecord Context [config]
  protocols.context/IContext
  (valid? [this]
    this)
  (shutdown! [this]
    this))

(s/defn new-context :- protocols.context/IContext
  [config]
  (->Context config))
