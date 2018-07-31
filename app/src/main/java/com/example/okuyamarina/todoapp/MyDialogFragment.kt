package com.example.okuyamarina.todoapp

import android.content.DialogInterface
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.app.Activity
import android.util.Log
import android.view.View
import android.widget.EditText
import kotlinx.android.synthetic.main.dialog_todo_input.*
import kotlinx.android.synthetic.main.dialog_todo_input.view.*
import java.text.FieldPosition


class NoticeDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(context)

        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_todo_input, null)
        builder.setView(dialogView)

        builder.setMessage(R.string.todo_input_title)
                .setPositiveButton("決定") { dialogInterface, i ->
                    //var text = dialogView.findViewById<EditText>(R.id.todo_text).text.toString()
                    var text = dialogView.todo_text.text.toString()
                    mListener.onDialogPositiveClick(text)
                }
                .setNegativeButton(R.string.cancel, DialogInterface.OnClickListener { dialog, id ->
                })
        return builder.create()
    }

    interface NoticeDialogListener {
        fun onDialogPositiveClick(text : String)
        fun onDialogNegativeClick(dialog: DialogFragment)
    }
    private lateinit var mListener: NoticeDialogListener

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        try {
            mListener = activity as NoticeDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException(activity!!.toString() + " must implement NoticeDialogListener")
        }
    }
}

class EditDialogFragment() : DialogFragment() {

    companion object {
        private val ARGS_NAME = "name"
        private val ARGS_POS = "pos"
        fun newInstance(name: String, position: Int): EditDialogFragment {
            val fragment = EditDialogFragment()

            val args = Bundle()
            args.putString(ARGS_NAME, name)
            args.putInt(ARGS_POS, position)
            fragment.setArguments(args)

            return fragment
        }
    }


    private var mName: String? = ""
    private var mPos : Int? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(getActivity())
        val dialogView = LayoutInflater.from(activity).inflate(R.layout.dialog_todo_input, null)
        builder.setView(dialogView)
        mName = arguments?.getString(ARGS_NAME)
        mPos = arguments?.getInt(ARGS_POS)

        dialogView.todo_text.setText(mName)

        builder.setMessage(R.string.todo_edit_title)
                .setPositiveButton("決定") { dialogInterface, i ->
//                    transaction.replace(R.id.my_recycler_view, EditDialogFragment.newInstance("myName"))
//                    transaction.commit()
                    var text = dialogView.todo_text.text.toString()
                    var position = mPos
                    if (position != null) {
                        mListener.onEditDialogPositiveClick(text, position)
                    }
                }
                .setNegativeButton(R.string.cancel, DialogInterface.OnClickListener { dialog, id ->
                })
        return builder.create()
    }

    interface EditDialogListener {
        fun onEditDialogPositiveClick(text : String, position: Int)
        fun onDialogNegativeClick()
    }

    private lateinit var mListener: EditDialogListener

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        try {
            mListener = activity as EditDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException(activity!!.toString() + " must implement EditDialogListener")
        }
    }
}