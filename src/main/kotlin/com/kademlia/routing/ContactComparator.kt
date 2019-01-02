package com.kademlia.routing

/**
 * Find the distance between one node and two other.
 * Given an initial node N and two other nodes P and Q
 * find if the distance N ---> P is greater than P ---> Q.
 *
 * @param contact represents N
 */
class ContactComparator(contact: Contact) : Comparator<Contact> {
    private var key = contact.nodeId.toInt()

    /**
     * Compare two objects of type <code>Node</code>
     * and determine which is closest to the node specified in the
     * constructor.
     *
     * @param n1 Node 1 to compare distance from the key
     * @param n2 Node 2 to compare distance from the key
     */
    override fun compare(o1: Contact?, o2: Contact?): Int {
        o1 ?: return -1
        o2 ?: return -1
        val k1 = o1.nodeId.toInt()
        val k2 = o2.nodeId.toInt()
        val x1 = key.xor(k1).abs()
        val x2 = key.xor(k2).abs()
        return x1.compareTo(x2)
    }
}