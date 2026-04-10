package com.example.expensetracker.view.mainview
import android.annotation.SuppressLint
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.expensetracker.R
import com.example.expensetracker.data.Epsemodel
import com.example.expensetracker.databinding.FragmentUseruploadBinding
import com.example.expensetracker.syntex
import com.example.expensetracker.view.basefrag.Basefragment
import com.example.expensetracker.viewmodel.Userincome_vmodel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.collections.forEach

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

             userincomeVmodel.getuseramount()

            val total =  sumtiondata(it)

            userincomeVmodel.useramount.observe(viewLifecycleOwner){it->

                val usertotal = it.toInt() - total

                userincomeVmodel.postuserdata(usertotal)

            }


         }

    }

    @SuppressLint("SuspiciousIndentation")
    private fun sumtiondata(binding1: FragmentUseruploadBinding): Int {

        var additems = 0




        binding1.let {

            val fooditems = it.etFood.syntex().toIntOrNull()

            val mediitems = it.etMedical.syntex().toIntOrNull()

            val travelitems = it.etTravel.syntex().toIntOrNull()

            val transitems = it.etTrans.syntex().toIntOrNull()

            val billitems = it.etBill.syntex().toIntOrNull()

            val cloitems = it.etClo.syntex().toIntOrNull()

            val otheritems = it.etOthers.syntex().toIntOrNull()


            val elist: List<Int?> = listOf(fooditems,
                mediitems,
                travelitems,
                transitems,
                billitems,
                cloitems,
                otheritems
                )

            val itemsuser: MutableList<Epsemodel> = mutableListOf()


            elist.forEach {it->

                it?.let { it1->

                     additems+=it1
                }


            }

            val mdata = Epsemodel(fooditems,mediitems,
                travelitems,
                transitems,
                billitems,
                cloitems,
                otheritems)

            itemsuser.add(mdata)

            userincomeVmodel.expenseadd(itemsuser)
        }

         return  additems
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
