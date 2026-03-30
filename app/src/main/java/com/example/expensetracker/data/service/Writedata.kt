package com.example.expensetracker.data.service

import com.example.expensetracker.Instancefirebase
import com.example.expensetracker.data.model.Amount
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore

interface Writedata {

    fun writeamount(amount: Int)

    fun getwamount()


}