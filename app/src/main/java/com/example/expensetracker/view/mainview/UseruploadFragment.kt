package com.example.expensetracker.view.mainview
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.expensetracker.R
import com.example.expensetracker.activity.MainActivity
import com.example.expensetracker.data.model.Epsemodel
import com.example.expensetracker.databinding.FragmentUseruploadBinding
import com.example.expensetracker.syntex
import com.example.expensetracker.view.basefrag.Basefragment
import com.example.expensetracker.viewmodel.Userincome_vmodel
import dagger.hilt.android.AndroidEntryPoint
import com.example.expensetracker.data.DataState
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.collections.forEach

@AndroidEntryPoint
class UseruploadFragment : Basefragment<FragmentUseruploadBinding>(

    FragmentUseruploadBinding::inflate

) {

    private val userincomeVmodel: Userincome_vmodel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun createuser() {

       toplevelmenubar()

       binding.save.setOnClickListener {

           userexpenseadd()

       }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun userexpenseadd() {


         binding.let {

            val total = sumtiondata(it)

            userincomeVmodel.total_expense(total)
             
         }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SuspiciousIndentation")
    private fun sumtiondata(binding1: FragmentUseruploadBinding): Int {

        var additems = 0

        binding1.let {

            val fooditems = it.etFood.syntex().toIntOrNull()?:0

            val mediitems = it.etMedical.syntex().toIntOrNull()?:0

            val travelitems = it.etTravel.syntex().toIntOrNull()?:0

            val transitems = it.etTrans.syntex().toIntOrNull()?:0

            val billitems = it.etBill.syntex().toIntOrNull()?:0

            val cloitems = it.etClo.syntex().toIntOrNull()?:0

            val otheritems = it.etOthers.syntex().toIntOrNull()?:0


            val elist: List<Int?> = listOf(fooditems,
                mediitems,
                travelitems,
                transitems,
                billitems,
                cloitems,
                otheritems
                )

            elist.forEach {it->

                it?.let { it1->

                     additems+=it1
                }
            }

            val sdf = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())

            val date = sdf.format(Date())

            val mdata = Epsemodel(date,fooditems,
                mediitems,travelitems,
                transitems,
                billitems,
                cloitems,
                otheritems
               )

            if (it.etFood.syntex().isEmpty()

                && it.etMedical.syntex().isEmpty() && it.etTravel.syntex().isEmpty()

                && it.etTrans.syntex().isEmpty() && it.etClo.syntex().isEmpty()

                && it.etBill.syntex().isEmpty() && it.etOthers.syntex().isEmpty()){

                Toast.makeText(requireContext(),"No Amount found", Toast.LENGTH_LONG).show()
            }

            else{
                createdatauser(mdata)
            }
        }

         return  additems
    }

    private fun createdatauser(mdata: Epsemodel) {

        userincomeVmodel.expenseadd(mdata)

        userrespons()
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

               userincomeVmodel.total_expense(0)

               userrespons()

               alertdialoge.dismiss()

           }

        alertdialoge.show()

    }

    private fun userrespons() {

        userincomeVmodel.user_respons.observe(viewLifecycleOwner){it->

            when(it){
                is DataState.Error->{

                    Toast.makeText(context,"${it.massage}", Toast.LENGTH_SHORT).show()

                }
                is DataState.Loading->{

                    Toast.makeText(context,"Loading...", Toast.LENGTH_SHORT).show()

                }
                is DataState.Success->{

                    Toast.makeText(context,"Successfully Create", Toast.LENGTH_LONG).show()

                    startActivity(Intent(requireActivity(), MainActivity::class.java))


                }
            }
        }

    }


}
