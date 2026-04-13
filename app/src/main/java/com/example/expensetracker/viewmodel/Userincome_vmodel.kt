package com.example.expensetracker.viewmodel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.expensetracker.data.DataState
import com.example.expensetracker.data.model.Epsemodel
import com.example.expensetracker.data.repository.Wamntrepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class Userincome_vmodel @Inject constructor(private val wamntrepo: Wamntrepo) : ViewModel() {

      private val _user_respons = MutableLiveData<DataState<Int>>()

     val user_respons: LiveData<DataState<Int>> = _user_respons

   // private val _getamount_respons = MutableLiveData<DataState<Int>>()

   // val getamount_respons: LiveData<DataState<Int>> = _getamount_respons

    private val _get_amount = MutableLiveData<String>()

    val get_amount: LiveData<String> = _get_amount

    fun postuserdata(m_amount: Int){

       _user_respons.postValue(DataState.Loading())

        wamntrepo.writeamount(m_amount).addOnSuccessListener {m_amount

          _user_respons.postValue(DataState.Success(0))

        }.addOnFailureListener {error->

            _user_respons.postValue(DataState.Error(error.message.toString()))
        }
    }

    fun getuseramount(){

        wamntrepo.getamount { it->

        _get_amount.value  = it

        }
    }

    fun expenseadd(userexpense:Epsemodel){


        wamntrepo.getexpenselist(userexpense).addOnSuccessListener {user->

            _user_respons.postValue(DataState.Success(0))

        }.addOnFailureListener { error->

            _user_respons.postValue(DataState.Error(error.message.toString()))

        }
    }

}