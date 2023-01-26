package com.ianJung.doItStatus.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.ianJung.doItStatus.model.PetItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DBViewModel(application: Application) : AndroidViewModel(application) {

    val db : FirebaseDatabase = FirebaseDatabase.getInstance()
    val Ref : DatabaseReference = db.getReference("MyItem")

    fun saveItem(item : PetItem){
        viewModelScope.launch(Dispatchers.IO) {
            Ref.push().setValue(item)
        }
    }
}