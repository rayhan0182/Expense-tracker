package com.example.expensetracker.viewmodel
import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.expensetracker.activity.MainActivity
import com.example.expensetracker.data.DataState
import com.example.expensetracker.data.model.Epsemodel
import com.example.expensetracker.data.repository.Wamntrepo
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class Userincome_vmodel @Inject constructor(private val wamntrepo: Wamntrepo,

    ) : ViewModel() {

      private val _user_respons = MutableLiveData<DataState<Int>>()

     val user_respons: LiveData<DataState<Int>> = _user_respons

    private val _itemslist = MutableLiveData<List<Epsemodel>>()

    val itemslist: LiveData<List<Epsemodel>> = _itemslist

    private val _get_amount_mutabledata = MutableLiveData<String>()

    val get_amount_livedata: LiveData<String> = _get_amount_mutabledata

    private val _get_expense_amount = MutableLiveData<String>()

    val get_expense_amount: LiveData<String> = _get_expense_amount

    fun postuserdata(m_amount: Int){

       _user_respons.postValue(DataState.Loading())

        wamntrepo.writeamount(m_amount).addOnSuccessListener {m_amount->

          _user_respons.postValue(DataState.Success(0))

        }.addOnFailureListener {error->

            _user_respons.postValue(DataState.Error(error.message.toString()))
        }
    }
    fun getuseramount(){

        wamntrepo.getamount { it->

         _get_amount_mutabledata.value = it

        }
    }
    fun expenseadd(userexpense:Epsemodel){
        
        wamntrepo.postexpenselist(userexpense).addOnSuccessListener {user->

            _user_respons.postValue(DataState.Success(0))

        }.addOnFailureListener { error->

            _user_respons.postValue(DataState.Error(error.message.toString()))
        }
    }

    fun get_expense_list(){

        wamntrepo.getexpenselist { it->

        _itemslist.value = it

        }
    }
    @SuppressLint("SuspiciousIndentation")
    fun total_expense(expense_add: Int){

        if (expense_add<1){

            wamntrepo.postexpenseamount(expense_add)

        }else {

            wamntrepo.getexpenseamount { it ->

                val count = it.toInt() + expense_add

                    wamntrepo.postexpenseamount(count)

            }

             }
        }
    fun get_total_expense(){

         wamntrepo.getexpenseamount { it->

         _get_expense_amount.value = it

         }
    }

}