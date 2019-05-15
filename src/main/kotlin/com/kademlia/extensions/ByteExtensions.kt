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

fun Byte.toBinaryString(): String {
    return String.format("%8s", Integer.toBinaryString(toInt() and 0xFF)).replace(' ', '0')
}

fun Int.toBinaryString():String{
    return String.format("%8s", Integer.toBinaryString(toInt() and 0xFF)).replace(' ', '0')
}

/**
 * Counts the number of leading zeros in a byte array.
 */
fun ByteArray.numberOfLeadingZeros(): Int {
    return map { it.numberOfLeadingZeros() }
            .takeWhile { it != 0 } // if this byte does not have any bits starting with zero, stop counting and go to next step
            .sum()
}

infix fun ByteArray.xor(other: ByteArray): ByteArray {
    if (this.size != other.size) throw IllegalArgumentException("Arrays have different lengths. They must be equal")
    return mapIndexed { index, byte -> byte xor other[index] }.toByteArray()
}

fun ByteArray.toBinaryString(separator: String = ""): String {
    return joinToString(separator) { it.toBinaryString() }
}
