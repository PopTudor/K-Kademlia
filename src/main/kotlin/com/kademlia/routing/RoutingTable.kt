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

    fun findClosest(contact: Contact, count: Int): List<Contact> {
        val prefLen = currentNode.id.distance(contact.id)
        val bucket = buckets[prefLen]
        val contacts = bucket.copyContacts()
        for (i in buckets.indices) {
            if (contacts.size >= count) {
                break
            }
            val leftIndex = i - prefLen
            val rightIndex = i + prefLen
            if (leftIndex <= 0 || rightIndex > buckets.size) {
                break
            }
            contacts.addAll(buckets[leftIndex].copyContacts())
            contacts.addAll(buckets[rightIndex].copyContacts())
        }
        if (contacts.size > buckets.size)
            (contacts.size downTo buckets.size).forEach {
                contacts.removeAt(it)
            }
        return contacts
    }
}