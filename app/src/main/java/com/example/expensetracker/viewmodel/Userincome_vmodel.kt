package com.example.expensetracker.viewmodel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.expensetracker.Constants
import com.example.expensetracker.data.repository.Wamntrepo
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class Userincome_vmodel @Inject constructor(private val wamntrepo: Wamntrepo) : ViewModel() {

      private val _useramount = MutableLiveData<String>()

     val useramount: LiveData<String> = _useramount

    fun postuserdata(m_amount: Int){

        wamntrepo.writeamount(m_amount)

    }

    fun getuseramount(){

      wamntrepo.getwamount {amount->

       _useramount.value = amount.toString()?:"0"

      }

    }

}