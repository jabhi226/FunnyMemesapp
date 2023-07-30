package com.example.funnymemesapp

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class UnitTest {
    @Test
    fun binarySearch() {
        assertEquals(
            2,
            Practice.binarySearch(intArrayOf(1, 4, 5, 7, 9, 12, 15), 5)
        )
    }

    @Test
    fun bubbleSort() {
        assertEquals(
            intArrayOf(1, 4, 5, 7, 9, 12, 15).joinToString(", "),
            Practice.bubbleSort(intArrayOf(9, 12, 7, 15, 1, 4, 5))
        )
    }

    @Test
    fun selectionSort() {
        assertEquals(
            intArrayOf(1, 4, 5, 7, 9, 12, 15).joinToString(", "),
            Practice.selectionSort(intArrayOf(3,6,1,7,2,0,Int.MIN_VALUE))
        )
    }

    @Test
    fun insertionSort() {
        assertEquals(
            intArrayOf(1, 2, 3, 4, 5, 6, 7, 8).joinToString(", "),
            Practice.insertionSort(intArrayOf(2, 8, 1, 3, 6, 7, 5, 4))
        )
    }

    @Test
    fun cyclicSort() {
        assertEquals(
            intArrayOf(1, 2, 3, 4, 5, 6, 7, 8).joinToString(", "),
            Practice.cyclicSort(intArrayOf(2, 8, 1, 3, 6, 7, 5, 4))
        )
    }

    @Test
    fun q1() {
        assertEquals(
            intArrayOf(1, 2, 3, 4, 5, 6, 7, 8).joinToString(", "),
            Practice.q1(intArrayOf(1, 4, 7, 8))
        )
    }

    @Test
    fun flatMap() {
        assertEquals(
            intArrayOf(1, 2, 3, 4, 5, 6, 7, 8).joinToString(", "),
            Practice.flatMap()
        )
    }
}