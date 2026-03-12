package com.example.expensetracker.view.basefrag
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class Basefragment<Vb: ViewBinding>(

    val inflatelayout:(inflate: LayoutInflater)->Vb

): Fragment() {

    private var _binding:Vb? = null

    val binding:Vb get() = _binding as Vb

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = inflatelayout.invoke(inflater)

        createuser()

        return binding.root
    }


    abstract fun createuser()

}