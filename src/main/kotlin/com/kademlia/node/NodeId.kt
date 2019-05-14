package com.kademlia.node

import com.kademlia.extensions.numberOfLeadingZeros
import com.kademlia.extensions.xor
import java.math.BigInteger
import java.security.MessageDigest
import java.util.*


// More shared bit pre-fix means closer distance between node ids
// This shared prefix will give leading zeros after the xor operation is done
// 0 xor 0 = 0
// 0 xor 1 = 1
// 1 xor 0 = 1
// 1 xor 1 = 0
// ex: D(11,10)
// 11: 1011
// 10: 1010
// xor---------
//     0001 = 1 distance
// we have 3 shared bits and distance is only 1! if we were to have less shared bits
// the distance would have been greater
// Notice that the longer the shared sequence of bits is, the more zeroes we have
// in the resulting number
class NodeId @Throws(IllegalArgumentException::class) constructor(key: String) : Comparable<NodeId> {
    val id: ByteArray = ByteArray(KEY_SIZE_BYTES)

    init {
        if (key.isEmpty()) { // init class with string. If string is empty, generate random sha
            val randomBytes = ByteArray(KEY_SIZE_BYTES)
            Random().nextBytes(randomBytes)
            getHash(randomBytes).copyInto(this.id)
        } else { // sha whatever we get and store it into id
            getHash(key.toByteArray()).copyInto(this.id)
        }
        if (this.id.size != KEY_SIZE_BYTES) { //
            throw IllegalArgumentException("Key must have $KEY_SIZE_BYTES bytes or ${KEY_SIZE_BYTES * 8} bits")
        }
    }

    private fun getHash(key: ByteArray) = MessageDigest.getInstance("SHA-1").digest(key)

    fun toInt() = BigInteger(id)

    fun toHex() = id.toHexString()

    fun printBinary(){
        id.forEach {
            print("${Integer.toBinaryString(it.toInt())} ")
        }
    }
    /**
     * Calculate the distance between this and another NodeId
     * @param other
     * @return The distance of this NodeId from the given NodeId
     */
    fun distance(other: NodeId) = this.id
            .xor(other.id)
            .numberOfLeadingZeros()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as NodeId

        if (!Arrays.equals(id, other.id)) return false

        return true
    }

    override fun hashCode() = Arrays.hashCode(id)

    override fun compareTo(other: NodeId): Int {
        val k1 = toInt()
        val k2 = other.toInt()
        return k1.abs().compareTo(k2.abs())
    }


    override fun toString() = toHex()
}

const val KEY_SIZE_BYTES = 20
const val KEY_SIZE_LEN = KEY_SIZE_BYTES * 8

fun ByteArray.toHexString() = joinToString("") { String.format("%02x", it) }
