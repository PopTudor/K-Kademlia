package com.kademlia

import com.kademlia.extensions.toBinaryString
import com.kademlia.node.Node
import com.kademlia.node.NodeId
import com.kademlia.routing.RoutingTable
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.jupiter.api.Test

class ContactIdTest {

    @Test
    fun testRoutingTable() {
        val n1 = NodeId()
        val n2 = NodeId()
        val n3 = NodeId()
        println(n1)
        println(n2)
        println(n1.distanceTo(n2).toBinaryString())
        println(n1.distanceTo(n2))
        println(n1.distanceTo(n1))
        val routingTable = RoutingTable(n1)
        routingTable.add(n1)
        routingTable.add(n2)
        routingTable.add(n3)
        println(routingTable)
    }

    @Test
    fun testPing() {
        val n1 = Node()
        println(n1)
        GlobalScope.launch {
            n1.start()
        }
        val n = Node()
        n.ping(n1)
    }

    @Test
    fun testNotFindNodeLocalRoutingTable() {
        val n1 = Node()
        val n = Node()
        val found = n1.findNode(n)
        assert(!found)
    }

    @Test
    fun testFindNodeLocalRoutingTable() {
        val n1 = Node()
        val n = Node()
        n1.routingTable.add(n.id)
        val found = n1.findNode(n)
        assert(found)
    }

}