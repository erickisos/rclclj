(ns rclclj.components.context
  (:require
   [rclclj.models.context :as models.context]
   [rclclj.protocols.context :as protocols.context]
   [schema.core :as s]))

(s/defrecord Context [config :- models.context/Config]
  protocols.context/IContext
  (valid? [this]
    this)
  (shutdown! [this reason]
    (println reason)
    this))

(s/defn create-context :- protocols.context/IContext
  [config :- models.context/Config]
  (->Context config))
