package com.kademlia

import com.kademlia.node.NodeId

enum class Type {
    PING, PONG, FIND_NODE, FIND_VALUE, STORE
}

data class Message(
        var type: Type,
        var from: NodeId,
        var to: NodeId,
        var fileHash: String? = null,
        var contacts: MutableList<NodeId>? = null
)

class ConnectionManager