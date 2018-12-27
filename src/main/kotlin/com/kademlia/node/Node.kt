package com.kademlia.node

import java.net.InetAddress

data class Node(
        val nodeId: NodeId,
        val ip: InetAddress = InetAddress.getLocalHost(),
        val port: Int = 8008
)