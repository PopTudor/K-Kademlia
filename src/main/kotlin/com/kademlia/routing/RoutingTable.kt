package com.kademlia.routing

import com.kademlia.node.KEY_SIZE_LEN
import com.kademlia.node.NodeId

data class RoutingTable(val currentNode: Contact) {
    val buckets = ArrayList<Bucket>(KEY_SIZE_LEN)


}