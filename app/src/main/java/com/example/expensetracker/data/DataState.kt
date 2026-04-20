package com.example.expensetracker.data

sealed class DataState<T>(

    var massage: String? = null,

    var udata:Int? = null

)  {

    class Loading<T>(): DataState<T>()

    class Success<T>(s_massage: Int?): DataState<T>(udata = s_massage)

    class Error<T>(massag: String?): DataState<T>(massag)
}