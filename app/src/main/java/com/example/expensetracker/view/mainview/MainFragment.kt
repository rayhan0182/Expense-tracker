package com.example.expensetracker.view.mainview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.expensetracker.R
import com.example.expensetracker.databinding.FragmentMainBinding
import com.example.expensetracker.view.basefrag.Basefragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Basefragment<FragmentMainBinding>(

    FragmentMainBinding::inflate

) {
    override fun createuser() {

        with(binding){

            clickBtn.setOnClickListener {

                findNavController().navigate(R.id.action_mainFragment_to_useruploadFragment)

            }



        }

    }


}