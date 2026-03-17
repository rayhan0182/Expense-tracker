package com.example.expensetracker.viewmodel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.expensetracker.data.repository.Wamntrepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class Userincome_vmodel @Inject constructor(private val wamntrepo: Wamntrepo): ViewModel() {

    private val _responssetincome = MutableLiveData<String>()

     val responssetincome: LiveData<String> = MutableLiveData()


    fun postuserdata(m_amount: Int){

        wamntrepo.writeamount(m_amount)

    }


}