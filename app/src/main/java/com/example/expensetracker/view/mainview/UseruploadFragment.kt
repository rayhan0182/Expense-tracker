package com.example.expensetracker.view.mainview
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.expensetracker.R
import com.example.expensetracker.data.repository.Wamntrepo
import com.example.expensetracker.databinding.FragmentUseruploadBinding
import com.example.expensetracker.view.basefrag.Basefragment
import com.example.expensetracker.viewmodel.Userincome_vmodel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UseruploadFragment : Basefragment<FragmentUseruploadBinding>(

    FragmentUseruploadBinding::inflate

) {

    lateinit var userincomeVmodel: Userincome_vmodel


    override fun createuser() {

       toplevelmenubar()

    }

    private fun toplevelmenubar() {

        binding.topToolbar.setOnMenuItemClickListener(){menuinflate->

            when(menuinflate.itemId){

                R.id.menu_income->{

                    showincomedialoge()
                    true
                }

                R.id.menu_expense->{

                    Toast.makeText(requireContext(),"hello expense", Toast.LENGTH_LONG).show()
                    true
                }

                else -> false

            }

        }

    }

    @SuppressLint("MissingInflatedId", "SuspiciousIndentation")
    private fun showincomedialoge() {

       val dialogeinflate = layoutInflater.inflate(R.layout.enteruserincome,null)

       val alertdialoge = android.app.AlertDialog.Builder(requireContext())

           .setView(dialogeinflate)

           .create()

           val amount = dialogeinflate.findViewById<EditText>(R.id.et_amount)

           val clickbtn = dialogeinflate.findViewById<Button>(R.id.click_btn)

           clickbtn.setOnClickListener {

               val useramount = amount.text.toString().toInt()

               userincomeVmodel = ViewModelProvider.create(this)[Userincome_vmodel::class.java]

               userincomeVmodel.postuserdata(useramount)

               alertdialoge.dismiss()

           }

        alertdialoge.show()

    }


}
