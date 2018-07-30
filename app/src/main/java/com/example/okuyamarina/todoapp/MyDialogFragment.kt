package com.example.okuyamarina.todoapp

import android.content.DialogInterface
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.app.Activity
import android.view.View
import android.widget.EditText


class MyDialogFragment : DialogFragment() {
    var todoTitle = ""

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Use the Builder class for convenient dialog construction
        val builder = AlertDialog.Builder(activity)
        // Get the layout inflater
        val inflater : LayoutInflater  = getActivity()?.getLayoutInflater()!!

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        todoTitle = builder.setView(inflater.inflate(R.layout.dialog_todo_input, null)).toString()

        builder.setMessage(R.string.todo_input_title)
                .setPositiveButton(R.string.ok, DialogInterface.OnClickListener { dialog, id ->
                })
                .setNegativeButton(R.string.cancel, DialogInterface.OnClickListener { dialog, id ->
                })
        // Create the AlertDialog object and return it
        return builder.create()
    }

}
class NoticeDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Use the Builder class for convenient dialog construction
        val builder = AlertDialog.Builder(getActivity())
        // Get the layout inflater
//        val inflater : LayoutInflater = getActivity()?.getLayoutInflater()!!


        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        val dialogView = LayoutInflater.from(activity).inflate(R.layout.dialog_todo_input, null)
        builder.setView(dialogView)

        builder.setMessage(R.string.todo_input_title)
                .setPositiveButton("決定") { dialogInterface, i ->
                    var text = dialogView.findViewById<EditText>(R.id.todo_text).text.toString()
                    mListener.onDialogPositiveClick(text)
                }
                .setNegativeButton(R.string.cancel, DialogInterface.OnClickListener { dialog, id ->
                })
        // Create the AlertDialog object and return it
        return builder.create()
    }

    interface NoticeDialogListener {
        public fun onDialogPositiveClick(text : String)
        fun onDialogNegativeClick(dialog: DialogFragment)
    }

    // Use this instance of the interface to deliver action events
    private lateinit var mListener: NoticeDialogListener

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = activity as NoticeDialogListener
        } catch (e: ClassCastException) {
            // The activity doesn't implement the interface, throw exception
            throw ClassCastException(activity!!.toString() + " must implement NoticeDialogListener")
        }

    }
}
