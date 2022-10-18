(ns rclclj.components.publisher
  (:require
   [rclclj.protocols.publisher :as protocols.publisher]
   [schema.core :as s]))

(s/defrecord Publisher [type  :- s/AnythingSchema
                        topic :- s/Str]
  protocols.publisher/IPublisher
  (publish! [_this _message]
    ;; TODO: Add the custom process to publish a message into ros2 network
    nil)
  (topic! [_]
    topic))

(s/defn create-publisher :- protocols.publisher/IPublisher
  [type  :- s/AnythingSchema
   topic :- s/Str]
  (->Publisher type topic))
