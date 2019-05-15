package com.kademlia

import com.kademlia.extensions.toBinaryString
import com.kademlia.routing.Contact
import com.kademlia.routing.RoutingTable
import org.junit.jupiter.api.Test

class ContactIdTest {

    @Test
    fun testRoutingTable() {
        val n1 = Contact()
        val n2 = Contact()
        val n3 = Contact()
        println(n1)
        println(n2)
        println(n1.distance(n2).toBinaryString())
        println(n1.distance(n2))
        println(n1.distance(n1))
        val routingTable = RoutingTable(n1)
        routingTable.add(n1)
        routingTable.add(n2)
        routingTable.add(n3)
        println(routingTable)
    }

}