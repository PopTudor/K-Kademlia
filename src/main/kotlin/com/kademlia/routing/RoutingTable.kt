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
        val prefixLenIndex = currentNode.distanceTo(contact)
        if (prefixLenIndex == KEY_SIZE_LEN) {
            buckets[prefixLenIndex - 1].add(contact)
            return
        }
        buckets[prefixLenIndex].add(contact)
    }

    fun findNode(node: NodeId): NodeId? {
        val distance = currentNode.distanceTo(node)
        return buckets[distance].find(node)
    }

    /**
     * Iterates trough the buckets in an attempt to find the best one.
     * First it tries an exact match. If none is found, look at the buckets closest
     * to this node. If none is found, look at the buckets furthest from current node.
     * If nothing is found, we got an empty routing table so just return an empty bucket
     */
    fun findClosestBucket(node: NodeId): Bucket {
        var distanceTo = currentNode.distanceTo(node)
        if (distanceTo == KEY_SIZE_LEN) distanceTo--

        var closestBuckets = buckets[distanceTo]

        //if the bucket at the distance we are interested in has some contacts, return here else go further
        if (!closestBuckets.isEmpty()) return closestBuckets

        // get the closest buckets starting with the bucket that we need up to the closest to our node
        // we need to pick better buckets in case the bucket we are looking for is empty
        for (i in distanceTo until KEY_SIZE_LEN) {
            val bucket = buckets[i]
            if (!bucket.isEmpty()) {
                closestBuckets = bucket
                break
            }
        }
        // could we not find any nodes in the closest buckets? let's look in the further buckets
        if (closestBuckets.isEmpty()) {
            for (i in distanceTo..0) {
                val bucket = buckets[i]
                if (!bucket.isEmpty()) {
                    closestBuckets = bucket
                    break
                }
            }
        }
        return closestBuckets
    }
}