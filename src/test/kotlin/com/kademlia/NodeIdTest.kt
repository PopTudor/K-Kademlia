package com.kademlia

import com.google.common.io.BaseEncoding
import com.kademlia.node.NodeId
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class NodeIdTest {

    @Test
    fun emptyNodeId_notThrowsException() {
        Assertions.assertDoesNotThrow<NodeId> {
            val n = NodeId("")

            n
        }
    }

    @Test
    fun testNodeValidHasId() {
        val nodeId = NodeId("random sha3 key")
        val nodeIdEncoded = BaseEncoding.base16().encode(nodeId.key)
        println("$nodeIdEncoded, ${nodeId.key} = ${nodeId.key.size}")
        Assertions.assertNotNull(nodeId.key)
        Assertions.assertTrue(nodeId.key.size == NodeId.KEY_SIZE_BYTES, "Invalid node length")
    }

    @Test
    fun testXor() {
        val n1 = NodeId("0")
        val n2 = NodeId("1")
        val n3 = NodeId("2")
        println("$n1 $n2 $n3")
        val d1 = n1.xor(n2)
        println(d1)
        val d2 = n1.xor(n3)
        println(d2)
    }
}