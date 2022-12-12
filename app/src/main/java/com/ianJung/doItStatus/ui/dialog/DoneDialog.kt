package com.ianJung.doItStatus.ui.dialog

import android.app.Activity
import android.app.Application
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.ianJung.doItStatus.R
import com.ianJung.doItStatus.adapter.TodoAdapter
import com.ianJung.doItStatus.databinding.FragmentDoneListBinding
import com.ianJung.doItStatus.databinding.FragmentShopBinding
import com.ianJung.doItStatus.databinding.FragmentTodoListBinding
import com.ianJung.doItStatus.databinding.TodoItemBinding
import com.ianJung.doItStatus.model.Memo
import com.ianJung.doItStatus.singlebungle.Singleton
import com.ianJung.doItStatus.ui.fragment.TodoListFragment
import com.ianJung.doItStatus.viewmodel.MemoViewModel

class DoneDialog(context : Context) : Dialog(context) {
    private lateinit var memoViewModel: MemoViewModel
    private lateinit var todoAdapter: TodoAdapter
    private lateinit var binding: TodoItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        memoViewModel = MemoViewModel(Application())
        todoAdapter= TodoAdapter(memoViewModel)
        binding=TodoItemBinding.inflate(layoutInflater)
        setContentView(R.layout.layout_donedialog)



        var okButton : Button = findViewById(R.id.doneButton)
        var cancelButton : Button = findViewById(R.id.noButton)

        // 배경 투명하게 바꿔줌
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        okButton.setOnClickListener {
            Singleton.gold = Singleton.gold?.plus(1)
            Singleton.exp=Singleton.exp?.plus(1)
            Toast.makeText(context, "Gold = ${Singleton.gold}, Exp = ${Singleton.exp}", Toast.LENGTH_LONG).show()
            dismiss()
        }

        // 취소 버튼 클릭 시 종료
        cancelButton.setOnClickListener { dismiss()}
    }
}
