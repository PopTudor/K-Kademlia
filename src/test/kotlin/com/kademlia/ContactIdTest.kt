package com.kademlia

import com.kademlia.extensions.numberOfLeadingZeros
import org.junit.jupiter.api.Test

class ContactIdTest {

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
}