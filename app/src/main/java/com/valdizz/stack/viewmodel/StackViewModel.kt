package com.valdizz.stack.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.valdizz.stack.model.Stack
import java.util.*

class StackViewModel(application: Application) : AndroidViewModel(application) {

    private val stackLiveData: MutableLiveData<List<String?>> = MutableLiveData()
    private val isEmptyStack: MutableLiveData<Boolean> = MutableLiveData()
    private val stack: Stack<String> = Stack()

    init {
        isEmptyStack.setValue(false)
    }

    fun getStack(): LiveData<List<String?>> {
        return stackLiveData
    }

    fun getIsEmptyStack(): LiveData<Boolean> {
        return isEmptyStack
    }

    fun pushStack() {
        val value = ('A'..'z').map { it }.shuffled().subList(0, 10).joinToString("")
        stack.push(value)
        stackLiveData.postValue(stack.toList())
        isEmptyStack.setValue(false)
    }

    fun popStack() {
        try {
            stack.pop()
            stackLiveData.postValue(stack.toList())
        }
        catch (e: EmptyStackException) {
            isEmptyStack.setValue(true)
        }
    }
}