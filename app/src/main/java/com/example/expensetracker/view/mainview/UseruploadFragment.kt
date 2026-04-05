package com.example.expensetracker.view.mainview
import android.annotation.SuppressLint
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.expensetracker.R
import com.example.expensetracker.data.Epsemodel
import com.example.expensetracker.databinding.FragmentUseruploadBinding
import com.example.expensetracker.syntex
import com.example.expensetracker.view.basefrag.Basefragment
import com.example.expensetracker.viewmodel.Userincome_vmodel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UseruploadFragment : Basefragment<FragmentUseruploadBinding>(

    FragmentUseruploadBinding::inflate

) {

    private val userincomeVmodel: Userincome_vmodel by viewModels()


    override fun createuser() {

       toplevelmenubar()

       binding.save.setOnClickListener {

           userexpenseadd()

       }

    }

    private fun userexpenseadd() {


         binding.let {

             val foodd = it.etFood.syntex()

             val medii = it.etMedical.syntex()

             val trav = it.etTravel.syntex()

             val clo = it.etClothers.syntex()

             val trans  = it.etTnsprt.syntex()

             val mobill = it.etBill.syntex()

             val othbill = it.etOthers.syntex()

             val total = foodd.toInt()+medii.toInt()+trav.toInt()+ clo.toInt()+trans.toInt()+mobill.toInt()+othbill.toInt()

             userincomeVmodel.getuseramount()

            userincomeVmodel.useramount.observe(viewLifecycleOwner){it->

                val usertotal = it.toInt() - total

                userincomeVmodel.postuserdata(usertotal)

            }
             val useramount: List<Epsemodel> = listOf(Epsemodel(foodd.toInt(),

                 medii.toInt(),trav.toInt(),clo.toInt(),trans.toInt(),

                 mobill.toInt(),othbill.toInt()

             ) )

             userincomeVmodel.expenseadd(useramount)


         }


    }

    private fun toplevelmenubar() {

        binding.topToolbar.setOnMenuItemClickListener(){menuinflate->

            when(menuinflate.itemId){

                R.id.menu_income->{

                    showincomedialoge(userincomeVmodel)
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
    private fun showincomedialoge(userincomeVmodel: Userincome_vmodel) {

       val dialogeinflate = layoutInflater.inflate(R.layout.enteruserincome,null)

       val alertdialoge = android.app.AlertDialog.Builder(requireContext())

           .setView(dialogeinflate)

           .create()

           val amount = dialogeinflate.findViewById<EditText>(R.id.et_amount)

           val clickbtn = dialogeinflate.findViewById<Button>(R.id.click_btn)

           clickbtn.setOnClickListener {

               val useramount = amount.text.toString().toInt()

               userincomeVmodel.postuserdata(useramount)

               alertdialoge.dismiss()

           }

        alertdialoge.show()

    }


}
