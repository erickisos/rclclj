(ns rclclj.models.node
  (:require [schema.core :as s]))

(def config-skeleton {:name                {:schema s/Str}
                      :namespace           {:schema s/Str :required false}
                      :enable-rosout?      {:schema s/Bool :required false}
                      :parameter-overrides {}})
(s/defschema Config config-skeleton)
