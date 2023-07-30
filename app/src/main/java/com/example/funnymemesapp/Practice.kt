package com.example.funnymemesapp

object Practice {

    fun binarySearch(test: IntArray, target: Int): Int {
        var middle = test.size / 2
        var start = 0
        var end = test.size - 1

        while (target != test[middle]) {
            println("$middle | $start | $end")
            if (target < test[middle]) {
                start = 0
                end = middle
            } else {
                start = middle + 1
                end = test.size - 1
            }
            middle = (start + end) / 2
        }
        return middle
    }

    fun bubbleSort(array: IntArray): String {
        for (i in array.indices) {
            for (j in 0 until array.size - i - 1) {
                if (array[j] > array[j + 1]){
                    val temp = array[j]
                    array[j] = array[j + 1]
                    array[j + 1] = temp
                }
            }
        }
        return array.joinToString(", ")
    }

    fun selectionSort(array: IntArray): String {
        for (i in array.indices) {
            var maxIndex = 0
            for (j in 0 until array.size - i) {
                if (array[j] > array[maxIndex]) {
                    maxIndex = j
                }
            }
            val t = array[array.size - 1 - i]
            array[array.size - 1 - i] = array[maxIndex]
            array[maxIndex] = t
        }
        return array.joinToString(", ")
    }

//    9, 12, 7, 15, 1, 4, 5
    fun insertionSort(array: IntArray): String {
        for (i in 1 until array.size) {
            for (j in i downTo 1) {
                if (array[j] < array[j - 1]){
                    val t = array[j]
                    array[j] = array[j - 1]
                    array[j - 1] = t
                    break
                }
            }
        }
        return array.joinToString(", ")
    }

    // 1, 2, 3, 4, 5
    // todo properly
    fun cyclicSort(array: IntArray): String {
        var i = 0
        while (i < array.size) {
            if (array[i] == array[i + 1]) {
                i++
            } else {
                val t = array[i]
                array[i] = array[i + 1]
                array[i + 1] = t
            }
        }
        return array.joinToString(", ")
    }

    // 0, 1, 4, 7, 8
    fun q1(array: IntArray): String {
        val t = Test1(1212)
        test(t)
        println(t.a)
        var i: Int
        val missing = arrayListOf<Int>()
        for (i in 0 until array.size - 1) {
            val diff = array[i] - (i + missing.size)
            if (diff == 0) {
                continue
            } else {
                for (j in 0 until diff){
                    missing.add(i + missing.size)
                }
            }
        }
        return missing.joinToString(", ")
    }

    fun flatMap(){
        val l = listOf<List<Int>>(listOf(1,2,3), listOf(4,5,6))
        l.map {
            arrayOf(it)
        }

        print(l.flatMap{it}.map { it+10 })
    }

    private fun test(t: Test1) {
        t.a = 90909
        t.equals(null)
        t.hashCode()
        t.toString()
        t.copy(10)

        val t1 = Test2()
        t1.equals(null)
        t1.hashCode()
        t1.toString()


        val m = mapOf<Int, String>(1 to "", 2 to "")
        for ((a, b) in m){
            a.plus(1)
            b.length
        }

    }


    data class Test1(var a: Int = 10)
    class Test2(var a: Int = 10)



abstract class T1 {
    abstract fun f1()
}

class S1: T1(){
    override fun f1() {
        TODO("Not yet implemented")
    }

}



}



