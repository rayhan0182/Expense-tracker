package com.example.expensetracker.view.mainview

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.expensetracker.R
import com.example.expensetracker.Rc.Adapter
import com.example.expensetracker.data.model.Epsemodel
import com.example.expensetracker.databinding.FragmentMainBinding
import com.example.expensetracker.view.basefrag.Basefragment
import com.example.expensetracker.viewmodel.Retrive_vmodel
import com.example.expensetracker.viewmodel.Userincome_vmodel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Basefragment<FragmentMainBinding>(

    FragmentMainBinding::inflate

) {

    private val viewmodel: Userincome_vmodel by viewModels()

    private val datashort: Retrive_vmodel by viewModels()

    override fun createuser() {

        menufilter()

        items_show()

        observe()

        with(binding) {

            clickBtn.setOnClickListener {

                findNavController().navigate(R.id.action_mainFragment_to_useruploadFragment)

            }

        }
    }
    private fun menufilter() {

        binding.dropSortdata.setOnMenuItemClickListener() { menuItem ->

            when (menuItem.itemId) {

                R.id.highlow -> {

                    datashort.itemsdata()

                    datashort.sortitems.observe(viewLifecycleOwner){data->

                       val adapteruser = Adapter(data)

                        binding.rc.adapter = adapteruser

                    }

                    true

                }

                R.id.date -> {

                    true

                }


                else -> false
            }


        }
    }

    private fun observe() {

        with(binding){

            viewmodel.getuseramount()

            viewmodel.get_amount_livedata.observe(viewLifecycleOwner){amount->

                iAmount.text = amount.toString()
            }

            viewmodel.get_total_expense()

            viewmodel.get_expense_amount.observe(viewLifecycleOwner){userdata->

                userdata?.let {

                    with(binding){

                        etExamount.text = it

                    }

                }
            }


        }
    }

    private fun items_show() {

        viewmodel.get_expense_list()

        viewmodel.itemslist.observe(viewLifecycleOwner){it->

           val uadapter = Adapter(it)

           binding.rc.adapter = uadapter

        }

    }


}