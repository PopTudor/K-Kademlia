package com.kademlia.routing

import com.kademlia.node.KEY_SIZE_LEN
import com.kademlia.node.NodeId

data class RoutingTable(
        val currentNode: NodeId,
        // buckets with index closer to 0 store contacts further from the current node because they share less prefix bits
        // the current node is in the last bucket because shared prefix len is 160
        val buckets: ArrayList<Bucket> = ArrayList(KEY_SIZE_LEN)
) {
    init {
        for (i in 1..KEY_SIZE_LEN) buckets.add(Bucket())
    }
    fun add(contact: NodeId) {
        val prefixLenIndex = currentNode.distance(contact)
        if (prefixLenIndex == KEY_SIZE_LEN) {
            buckets[prefixLenIndex - 1].add(contact)
            return
        }
        buckets[prefixLenIndex].add(contact)
    }
}