package com.kademlia.routing

import java.util.*

const val NO_OF_CONTACTS = 20

/**
 * A bucket is a list of nodes(contacts) that stores nodes
 * @param bucketSize is α from Kademlia paper, default to 20
 */
data class Bucket(
        val bucketSize: Int = NO_OF_CONTACTS,
        private val list: LinkedList<Contact> = LinkedList()
) {

    fun contains(contact: Contact) = list.contains(contact)

    fun isFull() = list.size == bucketSize

    fun add(other: Contact) {
        if (list.contains(other)) {
            list.remove(other)
        }
        if (list.size == NO_OF_CONTACTS)
            list.removeLast()
        list.addFirst(other)
    }

    fun copyContacts() = list.toMutableList()
}