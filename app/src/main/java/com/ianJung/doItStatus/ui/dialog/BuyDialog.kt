package com.ianJung.doItStatus.ui.dialog

import android.app.Application
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.ianJung.doItStatus.R
import com.ianJung.doItStatus.databinding.LayoutBuydialogBinding
import com.ianJung.doItStatus.model.PetItem
import com.ianJung.doItStatus.viewmodel.DBViewModel


class BuyDialog: DialogFragment() {

    private lateinit var dbViewModel: DBViewModel

    private var cost : Int = 0
    private var name : String = "익명"
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = LayoutBuydialogBinding.inflate(inflater, container, false)
        val view = binding.root
        dbViewModel = DBViewModel(Application())
        binding.buyTextview.text = "${name}의 가격은 ${cost}입니다.\n구매하시겠습니까?"
        dialog?.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.buyButton.setOnClickListener(){
            Toast.makeText(context, "물품 구입", Toast.LENGTH_SHORT).show()
            dbViewModel.saveItem(PetItem(name,cost.toFloat()))
            val pref = context?.getSharedPreferences("pref", Context.MODE_PRIVATE)
            DoneDialog.gold = pref!!.getInt("gold",0)
            DoneDialog.gold -=cost
            pref.edit().putInt("gold", DoneDialog.gold).commit()
            dismiss()
        }
        binding.notBuyButton.setOnClickListener(){
            Toast.makeText(context, "물품 구입안함", Toast.LENGTH_SHORT).show()
            dismiss()
        }
        return view
    }
    fun setCost(name : String,cost : Int){
        this.cost = cost
        this.name = name
    }

}