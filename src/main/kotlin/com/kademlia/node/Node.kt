package com.kademlia.node

import com.kademlia.routing.Contact
import com.kademlia.routing.RoutingTable

class Node(
        val contact: Contact = Contact(),
        val routingTable: RoutingTable = RoutingTable(contact)
) {

}