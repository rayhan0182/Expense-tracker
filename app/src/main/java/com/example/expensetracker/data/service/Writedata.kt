package com.example.expensetracker.data.service

import com.example.expensetracker.data.model.Epsemodel
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore

interface Writedata {

    fun writeamount(amount: Int): Task<Void>

    fun getamount(result:(String)-> Unit)

    fun getexpenselist(expense: Epsemodel): Task<Void>


}