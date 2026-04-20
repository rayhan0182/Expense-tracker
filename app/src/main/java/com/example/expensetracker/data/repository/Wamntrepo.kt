package com.example.expensetracker.data.repository
import android.util.Log
import com.example.expensetracker.Constants
import com.example.expensetracker.Constants.ex_keyname
import com.example.expensetracker.data.model.Epsemodel
import com.example.expensetracker.data.model.ExpenseWrapper
import com.example.expensetracker.data.service.Writedata
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class Wamntrepo @Inject constructor(private val firestore:FirebaseFirestore): Writedata {

    override fun writeamount(amount: Int): Task<Void> {

        val postdata = mapOf(Constants.amount_key to amount)

     return firestore.collection(Constants.co_user)

            .document(Constants.do_user)

         .set(postdata)
    }



    override fun postexpenseamount(e_amount: Int?) {

        firestore.collection(Constants.co_user)

            .document(Constants.postex_docu)

            .update(Constants.post_ex_keyname,e_amount)

    }
    override fun getexpenseamount(eresult: (String)-> Unit) {

        firestore.collection(Constants.co_user)

            .document(Constants.postex_docu).get().addOnSuccessListener {data->

                val amountdata = data.get(Constants.post_ex_keyname)

               eresult(amountdata.toString())

            }.addOnFailureListener {error->

                eresult(error.toString())

            }
    }

    override fun getamount(result:(String)-> Unit) {

        firestore.collection(Constants.co_user).document(Constants.do_user)

            .get().addOnSuccessListener {documentSnapshot ->

                val amount = documentSnapshot.get(Constants.amount_key)

                result(amount.toString())

            }.addOnFailureListener {error->

                result(error.toString())
            }
    }

    override fun postexpenselist(expense:Epsemodel): Task<Void> {

        val userdata = mapOf(ex_keyname to FieldValue.arrayUnion(expense))

     return firestore.collection(Constants.ex_coll)
            .document(Constants.ex_docu)
            .update(userdata)

    }

    override fun getexpenselist(result: (List<Epsemodel>) -> Unit) {

        firestore.collection(Constants.ex_coll).document(Constants.ex_docu)

            .get().addOnSuccessListener { documentSnapshot ->

           val list =  documentSnapshot.toObject(ExpenseWrapper::class.java)?.Expenselist?:emptyList()

                result(list)


            }.addOnFailureListener {

              result(emptyList())

            }
    }
}






