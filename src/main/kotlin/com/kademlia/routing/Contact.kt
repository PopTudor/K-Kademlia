package com.kademlia.routing

import com.kademlia.node.NodeId
import java.net.InetAddress

/**
 * A contact is stored in the routing table
 */
data class Contact(
        val nodeId: NodeId,
        val ip: InetAddress = InetAddress.getLocalHost(),
        val port: Int = 8008
)