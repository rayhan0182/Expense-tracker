package com.example.expensetracker.data.repository
import android.annotation.SuppressLint
import com.example.expensetracker.Constants
import com.example.expensetracker.data.Epsemodel
import com.example.expensetracker.data.service.Writedata
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject
import kotlin.text.get

class Wamntrepo @Inject constructor(private val firestore:FirebaseFirestore): Writedata {

    override fun writeamount(amount: Int){

        val postdata = hashMapOf(Constants.amount_key to amount)

      firestore.collection(Constants.co_user)

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

    @SuppressLint("SuspiciousIndentation")
    override fun getexpenselist(expense: MutableList<Epsemodel>) {

      val euserdata = hashMapOf<String, MutableList<Epsemodel>>(Constants.ex_keyname to expense)

         firestore.collection(Constants.ex_coll)

             .document(Constants.ex_docu).set(euserdata)
    }
}






