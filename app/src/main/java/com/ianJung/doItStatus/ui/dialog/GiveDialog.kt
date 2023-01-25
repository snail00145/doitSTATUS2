package com.ianJung.doItStatus.ui.dialog

import android.app.Application
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
import com.ianJung.doItStatus.databinding.LayoutGivedialogBinding
import com.ianJung.doItStatus.viewmodel.DBViewModel


class GiveDialog: DialogFragment() {
    private lateinit var dbViewModel: DBViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = LayoutGivedialogBinding.inflate(inflater, container, false)
        val view = binding.root
        dbViewModel = DBViewModel(Application())
        dialog?.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.chooseBubble.setOnClickListener(){

        }

        binding.chooseOnigiri.setOnClickListener(){

        }

        binding.chooseCake.setOnClickListener(){

        }

        binding.choosePills.setOnClickListener(){

        }


        binding.noButton.setOnClickListener { dismiss()}
        return view
    }



}