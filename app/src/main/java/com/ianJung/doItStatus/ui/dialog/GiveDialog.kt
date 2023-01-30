package com.ianJung.doItStatus.ui.dialog

import android.app.Application
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
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
import com.ianJung.doItStatus.databinding.LayoutGivedialogBinding
import com.ianJung.doItStatus.sharedpre.App.Companion.gold
import com.ianJung.doItStatus.sharedpre.App.Companion.petexp
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
        val db : FirebaseDatabase = FirebaseDatabase.getInstance()
        val Ref : DatabaseReference = db.reference

        binding.chooseBubble.setOnClickListener(){
        Ref.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                var getBubble  = snapshot.child("버블티")
                if (getBubble.exists()){
                    if(getBubble.value.toString().toInt()>0){
                        petexp += 100
                        Toast.makeText(context, "펫 경험치 100 증가", Toast.LENGTH_SHORT).show()
                        db.reference.child("버블티").runTransaction(object : Transaction.Handler{

                            override fun doTransaction(currentData: MutableData): Transaction.Result {
                                if (currentData.value != null) {

                                    var count = currentData.value.toString().toInt()
                                    count -= 1
                                    Log.d("태그", "doTransaction: $count")
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
                    else{
                        Toast.makeText(context, "아이템이 존재하지 않습니다.", Toast.LENGTH_SHORT).show()
                    }
                }
                else{
                    Toast.makeText(context, "아이템이 존재하지 않습니다.", Toast.LENGTH_SHORT).show()
                }


                }

            override fun onCancelled(error: DatabaseError) {

            }


        })
        }

        binding.chooseOnigiri.setOnClickListener(){
            Ref.addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    var getOnigiri  = snapshot.child("주먹밥")
                    if (getOnigiri.exists()){
                        if(getOnigiri.value.toString().toInt()>0){
                            petexp += 200
                            Toast.makeText(context, "펫 경험치 200 증가", Toast.LENGTH_SHORT).show()
                            db.reference.child("주먹밥").runTransaction(object : Transaction.Handler{

                                override fun doTransaction(currentData: MutableData): Transaction.Result {
                                    if (currentData.value != null) {

                                        var count = currentData.value.toString().toInt()
                                        count -= 1
                                        Log.d("태그", "doTransaction: $count")
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
                        else{
                            Toast.makeText(context, "아이템이 존재하지 않습니다.", Toast.LENGTH_SHORT).show()
                        }
                    }
                    else{
                        Toast.makeText(context, "아이템이 존재하지 않습니다.", Toast.LENGTH_SHORT).show()
                    }


                }

                override fun onCancelled(error: DatabaseError) {

                }


            })
        }

        binding.chooseCake.setOnClickListener(){
            Ref.addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    var getCake  = snapshot.child("케이크")
                    if (getCake.exists()){
                        if(getCake.value.toString().toInt()>0){
                            petexp += 300
                            Toast.makeText(context, "펫 경험치 300 증가", Toast.LENGTH_SHORT).show()
                            db.reference.child("케이크").runTransaction(object : Transaction.Handler{

                                override fun doTransaction(currentData: MutableData): Transaction.Result {
                                    if (currentData.value != null) {

                                        var count = currentData.value.toString().toInt()
                                        count -= 1
                                        Log.d("태그", "doTransaction: $count")
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
                        else{
                            Toast.makeText(context, "아이템이 존재하지 않습니다.", Toast.LENGTH_SHORT).show()
                        }
                    }
                    else{
                        Toast.makeText(context, "아이템이 존재하지 않습니다.", Toast.LENGTH_SHORT).show()
                    }


                }

                override fun onCancelled(error: DatabaseError) {

                }


            })
        }

        binding.choosePills.setOnClickListener(){

        }


        binding.noButton.setOnClickListener { dismiss()}
        return view
    }



}