package com.kademlia

import com.kademlia.extensions.numberOfLeadingZeros
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
}