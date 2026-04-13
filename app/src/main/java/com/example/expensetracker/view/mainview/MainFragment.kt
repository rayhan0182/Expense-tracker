package com.example.expensetracker.view.mainview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.expensetracker.R
import com.example.expensetracker.databinding.FragmentMainBinding
import com.example.expensetracker.view.basefrag.Basefragment
import com.example.expensetracker.viewmodel.Userincome_vmodel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Basefragment<FragmentMainBinding>(

    FragmentMainBinding::inflate

) {

    private val viewmodel: Userincome_vmodel by viewModels()

    override fun createuser() {

        viewmodel.getuseramount()

        amountshow()

        with(binding){

            clickBtn.setOnClickListener {

                findNavController().navigate(R.id.action_mainFragment_to_useruploadFragment)

            }

        }
    }

    private fun amountshow() {

        viewmodel.get_amount.observe(viewLifecycleOwner){amount->

            binding.iAmount.text = amount

        }
    }


}