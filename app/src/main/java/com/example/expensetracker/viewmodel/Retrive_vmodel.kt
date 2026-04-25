package com.example.expensetracker.viewmodel
import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.expensetracker.Rc.Adapter
import com.example.expensetracker.activity.MainActivity
import com.example.expensetracker.data.DataState
import com.example.expensetracker.data.model.Epsemodel
import com.example.expensetracker.data.repository.Wamntrepo
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.let

@HiltViewModel
class Retrive_vmodel @Inject constructor(private val wamntrepo: Wamntrepo) : ViewModel() {



    fun itemsdata(){

        wamntrepo.getexpenselist { elist->

            val list = elist

            val sorteddata = list.sortedBy { it.food!!+it.med!! + it.trav!!

                +it.trans!! + it.bill!!+it.clo!! + it.others!! }.reversed()


            val adapteruser = Adapter(sorteddata)


        }

    }


}