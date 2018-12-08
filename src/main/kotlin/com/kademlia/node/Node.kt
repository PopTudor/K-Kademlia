package com.kademlia.node

import java.net.InetAddress

class Node(
        val nodeId: NodeId,
        val ip: InetAddress = InetAddress.getLocalHost(),
        val port: Int = 8000
) {
    init {

    }




}