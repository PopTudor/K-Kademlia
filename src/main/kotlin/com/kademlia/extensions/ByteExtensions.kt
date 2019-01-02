package com.kademlia.extensions

import kotlin.experimental.xor

/**
 * Returns the number of leading zeros in a bytes binary represenation
 */
fun Byte.numberOfLeadingZeros() = when {
    this < 0 -> 0
    this < 1 -> 8
    this < 2 -> 7
    this < 4 -> 6
    this < 8 -> 5
    this < 16 -> 4
    this < 32 -> 3
    this < 64 -> 2
    this < 128 -> 1
    else -> 0
}

/**
 * Counts the number of leading zeros in a byte array.
 */
fun ByteArray.numberOfLeadingZeros(): Int {
    var result = 0
    var temp: Int
    for (i in indices) {
        temp = this[i].numberOfLeadingZeros() // for this byte
        result += temp
        if (temp != 8)
            break
    }
    return result
}

fun ByteArray.xor(other: ByteArray): ByteArray {
    if (this.size != other.size) throw IllegalArgumentException("Arrays have different lengths. They must be equal")

    val result = ByteArray(this.size)
    for (i in 0 until this.size) {
        result[i] = this[i] xor other[i]
    }
    return result
}

