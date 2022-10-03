(ns rclclj.models.node
  (:require [schema.core :as s]))

(def config-skeleton {:name                {:schema s/Str}
                      :namespace           {:schema s/Str :required false}
                      :parameter-overrides {}})
(s/defschema Config config-skeleton)
