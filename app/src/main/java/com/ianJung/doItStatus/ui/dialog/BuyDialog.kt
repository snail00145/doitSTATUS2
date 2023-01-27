package com.ianJung.doItStatus.ui.dialog

import android.app.Application
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.google.firebase.database.*
import com.ianJung.doItStatus.R
import com.ianJung.doItStatus.databinding.LayoutBuydialogBinding
import com.ianJung.doItStatus.model.PetItem
import com.ianJung.doItStatus.sharedpre.App.Companion.gold
import com.ianJung.doItStatus.sharedpre.App.Companion.prefs
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
            gold = prefs!!.getGold()
            if(gold<cost){
                dismiss()
                Toast.makeText(context, "구입불가", Toast.LENGTH_SHORT).show()
            }

            else {
                Toast.makeText(context, "물품 구입", Toast.LENGTH_SHORT).show()
                addItem(name)
                Log.d("태그", name)
                gold -= cost
                prefs.editor.putInt("gold", gold).commit()
                dismiss()
            }

        }
        binding.notBuyButton.setOnClickListener(){
            Toast.makeText(context, "물품 구입안함", Toast.LENGTH_SHORT).show()
            dismiss()
        }
        return view
    }

    fun addItem(path: String) {

        val db : FirebaseDatabase = FirebaseDatabase.getInstance()
        db.reference.child(path).runTransaction(object : Transaction.Handler{

            override fun doTransaction(currentData: MutableData): Transaction.Result {
                if (currentData.value != null) {
                    var count = currentData.value.toString().toInt()
                    count += 1
                    Log.d("태그", "doTransaction: $count")
                    currentData.value = count

                }
                else{
                    var count =1
                    currentData.value = count
                }
                return Transaction.success(currentData)
            }

            override fun onComplete(
                error: DatabaseError?,
                committed: Boolean,
                currentData: DataSnapshot?
            ) {

            }

        })
    }

    fun setCost(name : String,cost : Int){
        this.cost = cost
        this.name = name
    }

}