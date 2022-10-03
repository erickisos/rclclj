(ns rclclj.core
  (:require [schema.core :as s]))

(s/defn init
  [args init-options]
  (let [context (global-context args init-options)]))
