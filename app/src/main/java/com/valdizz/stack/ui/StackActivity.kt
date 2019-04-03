package com.valdizz.stack.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.valdizz.stack.R
import com.valdizz.stack.viewmodel.StackViewModel
import kotlinx.android.synthetic.main.activity_stack.*

class StackActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stack)

        val stackRecyclerViewAdapter = StackRecyclerViewAdapter(this)
        recyclerViewStack.layoutManager = LinearLayoutManager(this)
        recyclerViewStack.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recyclerViewStack.adapter = stackRecyclerViewAdapter

        val stackViewModel = ViewModelProviders.of(this).get(StackViewModel::class.java)
        stackViewModel.getStack().observe(this, Observer { stack ->
            stackRecyclerViewAdapter.setStack(stack)
            recyclerViewStack.smoothScrollToPosition(stack.size)
        })
        stackViewModel.getIsEmptyStack().observe(this, Observer { isEmptyStack ->
            if (isEmptyStack) Toast.makeText(this, getString(R.string.empty_stack), Toast.LENGTH_SHORT).show()
        })

        btnPush.setOnClickListener {
            stackViewModel.pushStack()
        }

        btnPop.setOnClickListener {
            stackViewModel.popStack()
        }
    }

}
