package com.example.expensetracker.view.mainview
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import com.example.expensetracker.R
import com.example.expensetracker.databinding.FragmentUseruploadBinding
import com.example.expensetracker.view.basefrag.Basefragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UseruploadFragment : Basefragment<FragmentUseruploadBinding>(

    FragmentUseruploadBinding::inflate

) {

    override fun createuser() {

        requireActivity().addMenuProvider(object : MenuProvider {

            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.main_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {

                return when(menuItem.itemId){

                    R.id.mnu_expense -> {
                        Toast.makeText(requireContext(),"Add Click",Toast.LENGTH_SHORT).show()
                        true
                    }

                    else -> false
                }
            }

        }, viewLifecycleOwner)

    }



}
