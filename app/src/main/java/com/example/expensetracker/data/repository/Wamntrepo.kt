package com.example.expensetracker.data.repository
import android.annotation.SuppressLint
import com.example.expensetracker.Constants
import com.example.expensetracker.data.model.Epsemodel
import com.example.expensetracker.data.service.Writedata
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject
import kotlin.text.get

class Wamntrepo @Inject constructor(private val firestore:FirebaseFirestore): Writedata {

    override fun writeamount(amount: Int): Task<Void> {

        val postdata = mapOf(Constants.amount_key to amount)

     return firestore.collection(Constants.co_user)

            .document(Constants.do_user)

          .set(postdata)
    }

    override fun getamount(result: (String) -> Unit) {

        firestore.collection(Constants.co_user).document(Constants.do_user)

            .get().addOnSuccessListener {it->

                val a_data = it.get(Constants.amount_key)

                result(a_data.toString())

            }.addOnFailureListener {error->

                result(error.toString())

            }
    }


    override fun getexpenselist(expense:Epsemodel): Task<Void> {

        val userdata = mapOf(Constants.ex_keyname to FieldValue.arrayUnion(expense))

     return firestore.collection(Constants.ex_coll)
            .document(Constants.ex_docu)
            .update(userdata)

    }
}






