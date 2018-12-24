package com.kademlia

import com.google.common.io.BaseEncoding
import com.kademlia.node.NodeId
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class NodeIdTest {

    @Test
    fun emptyNodeId_throwsException() {
        Assertions.assertDoesNotThrow<NodeId> {
            NodeId("")
        }
    }

    @Test
    fun testNodeValidHasId() {
        val nodeId = NodeId("random sha3 key")
        val nodeIdEncoded = BaseEncoding.base16().encode(nodeId.key)
        println("$nodeIdEncoded, ${nodeId.key} = ${nodeId.key.size}")
        Assertions.assertNotNull(nodeId.key)
        Assertions.assertTrue(nodeId.key.size == NodeId.KEY_SIZE_BYTES,"Invalid node length")
    }
}