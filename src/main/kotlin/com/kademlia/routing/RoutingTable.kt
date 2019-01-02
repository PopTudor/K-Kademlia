package com.kademlia.routing

import com.kademlia.node.NodeId

data class RoutingTable(val currentNode: Contact) {
    val buckets = ArrayList<Bucket>(NodeId.KEY_SIZE_LEN)

    fun update(other: Contact) {
        val prefLen = currentNode.id.distance(other.id)
        val bucket = buckets[prefLen]
        val found = bucket.contains(other)
        if (found) {
            bucket.moveToFront(other)
            return
        }

        if (bucket.isFull()) {
            TODO("Handle insertion when the list is full by evicting old elements(at the end of the bucket) " +
                    "if they don't respond to a ping and then add it to the bucket somewhere or not")
        } else {
            bucket.addToFront(other)
        }
    }
}