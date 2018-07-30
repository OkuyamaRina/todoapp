package com.example.okuyamarina.todoapp

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.example.okuyamarina.todoapp.R.layout.dialog_todo_input
import kotlinx.android.synthetic.main.dialog_todo_input.*

class MainActivity : AppCompatActivity(), NoticeDialogFragment.NoticeDialogListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    // サイズ0の配列
    //private var myDataset = emptyArray<String>()
    private var myDataset: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewManager = LinearLayoutManager(this)
        viewAdapter = MyAdapter(myDataset)

        recyclerView = findViewById<RecyclerView>(R.id.my_recycler_view).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter
        }
        myDataset.add("A")
    }
    fun addToDo(view: View){
        val newFragment = NoticeDialogFragment()
        newFragment.show(supportFragmentManager,"test")
    }

    fun showNoticeDialog() {
        // Create an instance of the dialog fragment and show it
        val dialog = NoticeDialogFragment()
        dialog.show(supportFragmentManager, "NoticeDialogFragment")
    }
    override fun onDialogPositiveClick(text : String){
        // /EditText(テキスト)を取得し、アダプタに追加
        myDataset.add(text)
        viewAdapter.notifyDataSetChanged()
    }

    override fun onDialogNegativeClick(dialog: DialogFragment) {

    }

    fun setToDo(){
        //EditTextオブジェクト取得
        //val todo : EditText = todo_text
        val todo: EditText = findViewById(R.id.todo_text)
        // /EditText(テキスト)を取得し、アダプタに追加
        myDataset.add(todo.getText().toString())
    }
}