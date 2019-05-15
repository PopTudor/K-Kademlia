package com.kademlia

import com.kademlia.node.NodeId
import com.kademlia.routing.RoutingTable
import org.junit.jupiter.api.Test

class RoutingTableTest {
    @Test
    fun testFindClosestBucketFound() {
        val nid = NodeId()
        val table = RoutingTable(nid)

        val mid = NodeId()
        table.add(mid)

        val foundExactMatchBucket = table.findClosestBucket(mid)
        val missingBucket = table.findClosestBucket(nid)

        assert(!foundExactMatchBucket.isEmpty())
        assert(missingBucket.isEmpty())
    }

}