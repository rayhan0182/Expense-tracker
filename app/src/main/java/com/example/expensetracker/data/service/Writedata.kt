package com.example.expensetracker.data.service

import com.example.expensetracker.data.Epsemodel

interface Writedata {

    fun writeamount(amount: Int)

    fun getamount(result:(String)-> Unit)

    fun getexpenselist(expense: MutableList<Epsemodel>)


}