package com.ianJung.doItStatus.ui.dialog

import android.app.Activity
import android.app.Application
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.ianJung.doItStatus.R
import com.ianJung.doItStatus.adapter.TodoAdapter
import com.ianJung.doItStatus.databinding.FragmentShopBinding
import com.ianJung.doItStatus.databinding.FragmentTodoListBinding
import com.ianJung.doItStatus.databinding.TodoItemBinding
import com.ianJung.doItStatus.model.Memo
import com.ianJung.doItStatus.ui.fragment.TodoListFragment
import com.ianJung.doItStatus.viewmodel.MemoViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.prefs.PreferenceChangeEvent

class DoneDialog(context : Context) : Dialog(context) {
    private lateinit var memoViewModel: MemoViewModel
    private lateinit var todoAdapter: TodoAdapter
    private lateinit var binding: TodoItemBinding
    private lateinit var binding2: FragmentShopBinding
    private lateinit var memo : Memo
    companion object{
        var gold = 0
        var exp = 0
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        memoViewModel = MemoViewModel(Application())
        todoAdapter= TodoAdapter(memoViewModel)
        binding=TodoItemBinding.inflate(layoutInflater)
        binding2=FragmentShopBinding.inflate(layoutInflater)
        setContentView(R.layout.layout_donedialog)

        val pref = context.getSharedPreferences("pref", Context.MODE_PRIVATE)
        gold = pref.getInt("gold",0)
        exp = pref.getInt("exp", 0)
        binding2.Gold.setText(gold.toString())
        binding2.Exp.setText(exp.toString())

        var okButton : Button = findViewById(R.id.doneButton)
        var cancelButton : Button = findViewById(R.id.noButton)

        // ?????? ???????????? ?????????
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        okButton.setOnClickListener {
           // Singleton.gold = Singleton.gold?.plus(1)
            //Singleton.exp=Singleton.exp?.plus(1)
            gold+=10
            exp+=100
            binding2.Gold.setText(gold.toString())
            binding2.Exp.setText(exp.toString())
            Log.d("dd", gold.toString())
            Toast.makeText(context, "Gold = ${gold}, Exp = ${exp}", Toast.LENGTH_SHORT).show()
            pref.edit().putInt("gold", gold).commit()
            pref.edit().putInt("exp", exp).commit()
            dismiss()
            CoroutineScope(Dispatchers.IO).launch {
                memoViewModel.deleteMemo(memo)//????????? ??????
            }
        }

        // ?????? ?????? ?????? ??? ??????
        cancelButton.setOnClickListener { dismiss()}
    }
    fun setMemo(memo : Memo){
        this.memo = memo
    }




}
