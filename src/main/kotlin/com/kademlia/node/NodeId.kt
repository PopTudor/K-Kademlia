package com.kademlia.node

import com.google.common.hash.HashCode
import com.google.common.hash.Hashing
import com.kademlia.numberOfLeadingZeros
import java.math.BigInteger
import java.util.*
import kotlin.experimental.xor


data class NodeId(var key: ByteArray) : Comparable<NodeId> {
    @Throws(IllegalArgumentException::class)
    constructor(key: String) : this(key.toByteArray())

    init {
        when {
            key.isEmpty() -> {
                key = ByteArray(KEY_SIZE_BYTES)
                Random().nextBytes(key)
                key = getHash(key).asBytes()
            }
            key.size != KEY_SIZE_BYTES -> {
                val hash = getHash(key)
                key = hash.asBytes()
            }
            else -> {
            }
        }

        if (key.size != KEY_SIZE_BYTES) { // should never reach
            throw IllegalArgumentException("Key must have $KEY_SIZE_BYTES bytes or ${KEY_SIZE_BYTES * 8} bits")
        }
    }

    private fun getHash(key: ByteArray): HashCode {
        val hf = Hashing.sha256()
        return hf.newHasher()
                .putBytes(key)
                .hash()
    }

    fun toInt() = BigInteger(key)

    fun toHex() = key.toHexString()

    /**
     * Checks the distance between this and another NodeId
     * @param other
     * @return The distance of this NodeId from the given NodeId
     */
    fun xor(other: NodeId): NodeId {
        val result = ByteArray(KEY_SIZE_BYTES)
        for (i in 0 until KEY_SIZE_BYTES) {
            result[i] = this.key[i] xor other.key[i]
        }

        return NodeId(result)
    }

    fun numOfLeadingZeros() = this.key.numberOfLeadingZeros()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as NodeId

        if (!Arrays.equals(key, other.key)) return false

        return true
    }

    override fun hashCode() = Arrays.hashCode(key)

    override fun compareTo(other: NodeId): Int {
        val k1 = toInt()
        val k2 = other.toInt()
        return k1.abs().compareTo(k2.abs())
    }

    companion object {
        const val KEY_SIZE_BYTES = 32
    }

    override fun toString() = toHex()
}

fun ByteArray.toHexString() = joinToString("") { String.format("%02x", it) }
