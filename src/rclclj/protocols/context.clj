(ns rclclj.protocols.context)

(defprotocol IContext
  "Protocol for context management

  Usually this would be called with (rclclj/init)
  Creating a context will automatic parse all the command-line arguments"
  (valid? [this]
    "Checks if the current context is valid")
  (shutdown! [this reason]
    "Release resources"))
