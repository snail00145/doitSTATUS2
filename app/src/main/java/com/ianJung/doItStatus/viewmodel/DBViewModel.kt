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

    private val db : DatabaseReference = FirebaseDatabase.getInstance().reference

    fun saveItem(item : PetItem){
        viewModelScope.launch(Dispatchers.IO) {
            db.child("MyItem").push().setValue(item)
        }
    }
}