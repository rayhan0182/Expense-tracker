package com.example.expensetracker.Rc

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.expensetracker.data.model.Epsemodel
import com.example.expensetracker.databinding.UserItemslistBinding

class Adapter(private val items: List<Epsemodel>): RecyclerView.Adapter<Adapter.Viewholder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): Viewholder {



        val bind = UserItemslistBinding.inflate(LayoutInflater.from(parent.context),parent,false)

       return Viewholder(bind)

    }

    override fun onBindViewHolder(
        holder: Viewholder,
        position: Int
    ) {

       val alist = items[position]

      holder.binding.let {

          it.date.text = alist.date.toString()

          it.etFood.text = alist.food.toString()

          it.etMedi.text = alist.med.toString()

          it.etTravel.text = alist.trav.toString()

          it.etTransport.text = alist.trans.toString()

          it.etBill.text = alist.bill.toString()

          it.etClo.text = alist.clo.toString()

          it.etOthers.text = alist.others.toString()

      }

    }

    override fun getItemCount(): Int  = items.size

    inner class Viewholder(val binding: UserItemslistBinding): RecyclerView.ViewHolder(binding.root)

}