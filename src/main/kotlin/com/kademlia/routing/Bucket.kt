package com.kademlia.routing

import com.kademlia.node.NodeId
import java.util.*

const val NO_OF_CONTACTS = 20

/**
 * A bucket is a list of nodes(contacts) that stores nodes
 * @param bucketSize is α from Kademlia paper, default to 20
 */
data class Bucket(
        val bucketSize: Int = NO_OF_CONTACTS,
        private val list: LinkedList<NodeId> = LinkedList()
) {

    fun contains(contact: NodeId) = list.contains(contact)

    fun isFull() = list.size == bucketSize

    fun add(other: NodeId) {
        if (list.contains(other)) {
            list.remove(other)
        }
        if (list.size == bucketSize)
            list.removeLast()
        list.addFirst(other)
    }

    fun find(nodeId: NodeId):NodeId?{
        return list.firstOrNull { it.id.contentEquals(nodeId.id) }
    }

    fun copyContacts() = list.toMutableList()
}