package com.ianJung.doItStatus.ui.dialog

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

class BuyDialog: DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = LayoutBuydialogBinding.inflate(inflater, container, false)
        val view = binding.root
        dialog?.window!!.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))

        binding.buyButton.setOnClickListener(){
            Toast.makeText(context, "물품 구입", Toast.LENGTH_SHORT).show()
            dismiss()
        }
        binding.notBuyButton.setOnClickListener(){
            Toast.makeText(context, "물품 구입안함", Toast.LENGTH_SHORT).show()
            dismiss()
        }

        return view
    }


}