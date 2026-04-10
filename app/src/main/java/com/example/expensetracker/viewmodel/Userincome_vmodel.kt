package com.example.expensetracker.viewmodel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.expensetracker.data.Epsemodel
import com.example.expensetracker.data.repository.Wamntrepo
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


        wamntrepo.getamount { it->

        _useramount.value  = it

        }

    }

    fun expenseadd(userexpense: MutableList<Epsemodel>){

        wamntrepo.getexpenselist(userexpense)


    }


}