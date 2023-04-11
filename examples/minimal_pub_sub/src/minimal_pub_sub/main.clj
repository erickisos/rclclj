(ns minimal-pub-sub.main
  (:require [schema.core :as s]
            [rclclj.core :as ros]
            [rclclj.node :as node]))

(s/defn main
  []
  (let [context   {}
        publisher (node/create "minimal_publisher" context)]
    (ros/spin)))
