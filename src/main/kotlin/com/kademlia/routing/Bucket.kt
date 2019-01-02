package com.kademlia.routing

import java.util.*

/**
 * A bucket is a list of nodes(contacts) that stores nodes
 * @param bucketSize is α from Kademlia paper, default to 20
 */
data class Bucket(val bucketSize: Int = 20) {
    private val list = LinkedList<Contact>()

    fun contains(contact: Contact) = list.contains(contact)

    fun moveToFront(other: Contact) {
        list.remove(other)
        list.addFirst(other)
    }

    fun isFull() = list.size == bucketSize

    fun addToFront(other: Contact) {
        list.addFirst(other)
    }

    fun copyContacts() = list.toMutableList()
}