package com.kademlia.node

import java.math.BigInteger
import java.util.*
import kotlin.experimental.xor
import kotlin.random.Random

data class NodeId(var key: ByteArray) {
    constructor(key: String) : this(key.toByteArray())

    init {
        if (key.isEmpty()) {
            key = ByteArray(ID_LENGTH)
            Random.nextBytes(key)
        }

        if (key.size == ID_LENGTH) {
            throw IllegalArgumentException("Key must have $ID_LENGTH bytes or ${ID_LENGTH * 8} bits")
        }
    }

    fun toInt() = BigInteger(1, key)
    /**
     * Checks the distance between this and another NodeId
     * @param nodeId
     * @return The distance of this NodeId from the given NodeId
     */
    fun xor(nodeId: NodeId): NodeId {
        val result = ByteArray(ID_LENGTH)
        val nidBytes = nodeId.key

        for (i in 0 until ID_LENGTH)
            result[i] = key[i] xor nidBytes[i]

        return NodeId(result)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as NodeId

        if (!Arrays.equals(key, other.key)) return false

        return true
    }

    override fun hashCode(): Int {
        return Arrays.hashCode(key)
    }

    companion object {
        const val ID_LENGTH = 20
    }
}