package com.ianJung.doItStatus.ui.dialog

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



class GiveDialog: DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = LayoutGivedialogBinding.inflate(inflater, container, false)
        val view = binding.root

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