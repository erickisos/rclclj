(ns rclclj.components.subscriber
  (:require
   [rclclj.protocols.subscriber :as protocols.subscriber]
   [schema.core :as s]))

(s/defrecord Subscriber [type topic]
  protocols.subscriber/ISubscriber)
