(ns rclclj.protocols.context)

(defprotocol IContext
  "Protocol for context management"
  (valid? [this]
    "Checks if the current context is valid")
  (shutdown! [this reason]
    "Release resources"))
