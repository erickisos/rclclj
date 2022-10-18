(ns rclclj.protocols.node)

(defprotocol INode
  "Single entry point for publishers and subscribers management"
  (name! [this])
  (namespace! [this])
  (fully-qualified-name! [this])
  (logger! [this])
  (valid? [this])
  (spin! [this])
  (spin-once! [this])
  (rosout? [this, enable]))
