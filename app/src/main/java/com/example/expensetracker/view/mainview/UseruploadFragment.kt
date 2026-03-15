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
import androidx.navigation.fragment.findNavController
import com.example.expensetracker.R
import com.example.expensetracker.databinding.FragmentUseruploadBinding
import com.example.expensetracker.view.basefrag.Basefragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UseruploadFragment : Basefragment<FragmentUseruploadBinding>(

    FragmentUseruploadBinding::inflate

) {

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

        val dialogeview = layoutInflater.inflate(R.layout.enteruserincome,null)


        val dialoge = android.app.AlertDialog.Builder(requireContext())
            .setView(dialogeview)
            .create()

        val e_text = dialogeview.findViewById<EditText>(R.id.et_amount)

        val btn = dialogeview.findViewById<Button>(R.id.click_btn)


          btn.setOnClickListener {

              val income = e_text.text.toString()

              Toast.makeText(requireContext(),"Income: $income",Toast.LENGTH_SHORT).show()

              dialoge.dismiss()
          }

        dialoge.show()

    }


}
