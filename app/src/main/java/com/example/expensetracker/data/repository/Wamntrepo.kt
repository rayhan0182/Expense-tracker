package com.example.expensetracker.data.repository
import com.example.expensetracker.Constants
import com.example.expensetracker.data.model.Amount
import com.example.expensetracker.data.service.Writedata
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.local.LruGarbageCollector
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

class Wamntrepo @Inject constructor(private val firestore: FirebaseFirestore): Writedata {

    override fun writeamount(amount: Int){

        val postdata = hashMapOf("useramount" to amount)

      firestore.collection(Constants.co_user)

            .document(Constants.do_user)

     .set(postdata)

    }

}

