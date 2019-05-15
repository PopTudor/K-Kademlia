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
        val nodes: LinkedList<NodeId> = LinkedList()
) {

    fun contains(contact: NodeId) = nodes.contains(contact)

    fun isFull() = nodes.size == bucketSize

    fun add(other: NodeId) {
        if (nodes.contains(other)) {
            nodes.remove(other)
        }
        if (nodes.size == bucketSize)
            nodes.removeLast()
        nodes.addFirst(other)
    }

    fun find(nodeId: NodeId): NodeId? {
        return nodes.firstOrNull { it.id.contentEquals(nodeId.id) }
    }

    fun copyContacts() = nodes.toMutableList()
    fun isEmpty() = nodes.isEmpty()

}