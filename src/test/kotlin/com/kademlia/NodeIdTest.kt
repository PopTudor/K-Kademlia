package com.kademlia

import com.google.common.io.BaseEncoding
import com.kademlia.node.NodeId
import com.kademlia.node.toHexString
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
        println("$d1 len: ${d1.key.size}")
        val d2 = n1.xor(n3)
        println("$d2 len: ${d2.key.size}")

        val xorBigint1 = n1.toInt().xor(n2.toInt()).toByteArray().toHexString()
        val xorBigint2 = n1.toInt().xor(n3.toInt()).toByteArray().toHexString()

        Assertions.assertTrue(xorBigint1 == d1.toString())
        Assertions.assertTrue(xorBigint2 == d2.toString())
    }
}