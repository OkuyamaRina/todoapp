package com.example.okuyamarina.todoapp

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View

class MainActivity : AppCompatActivity(), NoticeDialogFragment.NoticeDialogListener, EditDialogFragment.EditDialogListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: MyAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    // サイズ0の配列
    //private var myDataset = emptyArray<String>()
    private var myDataset: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewManager = LinearLayoutManager(this)
        viewAdapter = MyAdapter(myDataset)
        viewAdapter.onClick = {text, position ->
            val dialog = EditDialogFragment.newInstance(text,position)
            dialog.show(supportFragmentManager, "EditDialogFragment")
        }
        viewAdapter.onLongClick = {
            myDataset.removeAt(it)
            viewAdapter.notifyDataSetChanged()
        }

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
        val dialog = NoticeDialogFragment()
        dialog.show(supportFragmentManager,"NoticeDialogFragment")
    }
    override fun onDialogPositiveClick(text : String){
        // /EditText(テキスト)を取得し、アダプタに追加
        myDataset.add(text)
        viewAdapter.notifyDataSetChanged()
    }

    override fun onEditDialogPositiveClick(text : String, position : Int){
        myDataset.set(position,text)
        viewAdapter.notifyDataSetChanged()
    }

    override fun onDialogNegativeClick(dialog: DialogFragment) {

    }
    override fun onDialogNegativeClick() {
    }
}
