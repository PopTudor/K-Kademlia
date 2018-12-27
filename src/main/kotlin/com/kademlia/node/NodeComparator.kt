package com.kademlia.node

/**
 * Find the distance between one node and two other.
 * Given an initial node N and two other nodes P and Q
 * find if the distance N ---> P is greater than P ---> Q.
 *
 * @param node represents N
 */
class NodeComparator(node: Node) : Comparator<Node> {
    private var key = node.nodeId.toInt()

    /**
     * Compare two objects of type <code>Node</code>
     * and determine which is closest to the node specified in the
     * constructor.
     *
     * @param n1 Node 1 to compare distance from the key
     * @param n2 Node 2 to compare distance from the key
     */
    override fun compare(o1: Node?, o2: Node?): Int {
        o1 ?: return -1
        o2 ?: return -1
        val k1 = o1.nodeId.toInt()
        val k2 = o2.nodeId.toInt()
        val x1 = key.xor(k1).abs()
        val x2 = key.xor(k2).abs()
        return x1.compareTo(x2)
    }
}