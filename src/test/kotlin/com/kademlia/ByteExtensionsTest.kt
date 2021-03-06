package com.kademlia

import com.kademlia.extensions.numberOfLeadingZeros
import com.kademlia.extensions.toBinaryString
import com.kademlia.extensions.xor
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ByteExtensionsTest {
    @Test
    fun testCountLeadingZerosEmpty() {
        val data: Byte = 0
        val count = data.numberOfLeadingZeros()
        Assertions.assertTrue(count == 8)
    }
    @Test
    fun testCountLeadingZeros() {
        val data7: Byte = 0b01
        val data6: Byte = 0b011
        val count7 = data7.numberOfLeadingZeros()
        val count6 = data6.numberOfLeadingZeros()
        Assertions.assertTrue(count7 == 7)
        Assertions.assertTrue(count6 == 6)
    }
    @Test
    fun testByteLeadingZeros() {
        val value: Byte = 0
        val value1: Byte = 1
        val value2: Byte = 2
        val value3: Byte = 3
        assert(value.numberOfLeadingZeros() == 8)
        assert(value1.numberOfLeadingZeros() == 7)
        assert(value2.numberOfLeadingZeros() == 6)
        assert(value3.numberOfLeadingZeros() == 6)
    }

    @Test
    fun testXorByteArray() {
        val zero = ByteArray(2) { 0 }
        val one = ByteArray(2) { 1 }
        val three = ByteArray(2) { 3 }
        val zeroOne = zero xor one
        println(zero.contentToString() + zero.toBinaryString(" "))
        println(one.contentToString() + one.toBinaryString(" "))
        println(three.contentToString() + three.toBinaryString(" "))
        println("xor 0^1" + zeroOne.contentToString() + zeroOne.toBinaryString(" "))
        val oneOne = one xor one
        println("xoe 1^1" + oneOne.contentToString() + oneOne.toBinaryString(" "))

        val oneThree = one.xor(three)
        println(oneThree.contentToString() + oneThree.toBinaryString(" "))
    }
}