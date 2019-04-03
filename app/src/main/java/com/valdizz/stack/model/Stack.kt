package com.valdizz.stack.model

import java.lang.IllegalArgumentException
import java.util.*


class Stack<E>(initialCapacity: Int = 10) {

    private val DEFAULT_CAPACITY = 10
    private val MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8
    private var stack: Array<E?>
    private var pointer = -1

    init {
        if (initialCapacity < 0)
            throw IllegalArgumentException("Illegal Capacity: $initialCapacity")
        stack = arrayOfNulls<Any?>(initialCapacity) as Array<E?>
    }

    fun push(item: E): E {
        if (isFull())
            growCapacity()
        stack[++pointer] = item
        return item
    }

    fun pop(): E {
        val obj = peek()
        stack[pointer--] = null
        return obj
    }

    fun peek(): E {
        if (isEmpty())
            throw EmptyStackException()
        return stack[pointer] as E
    }

    fun isEmpty() = pointer == -1

    fun isFull() = pointer == stack.size-1

    fun search(item: E): Int {
        return stack.binarySearch(item)
    }

    fun contains(item: E): Boolean {
        return stack.contains(item)
    }

    fun trimToSize() {
        if (pointer < stack.size && pointer > DEFAULT_CAPACITY) {
            stack = stack.copyOf(pointer + 1)
        }
    }

    fun clear() {
        stack.fill(null)
        pointer = -1
    }

    private fun growCapacity() {
        var newCapacity: Int = (stack.size * 1.5 + 1).toInt()
        if (newCapacity < DEFAULT_CAPACITY)
            newCapacity = DEFAULT_CAPACITY
        if (newCapacity > MAX_ARRAY_SIZE)
            newCapacity = MAX_ARRAY_SIZE
        stack = stack.copyOf(newCapacity)
    }

    fun toList(): List<E?> {
        return stack.copyOf(pointer + 1).toList()
    }

    override fun toString(): String {
        return "Stack(stack=${Arrays.toString(stack)}, size=${stack.size}, pointer=$pointer)"
    }


}