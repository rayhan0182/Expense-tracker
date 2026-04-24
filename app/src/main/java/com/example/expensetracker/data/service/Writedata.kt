package com.example.expensetracker.data.service

import com.example.expensetracker.data.model.Epsemodel
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore

interface Writedata {

    fun writeamount(amount: Int): Task<Void>

   fun postexpenseamount(e_amount: Int?)

   fun getexpenseamount(eresult:(String)-> Unit)

    fun getamount(result:(String)-> Unit)

    fun postexpenselist(expense: Epsemodel): Task<Void>

    fun getexpenselist(result:(List<Epsemodel>)-> Unit)



}