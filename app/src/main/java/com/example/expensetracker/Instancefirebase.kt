package com.example.expensetracker

import com.example.expensetracker.data.repository.Wamntrepo
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Instancefirebase {


    @Provides
    @Singleton

    fun firestorefirebase(): FirebaseFirestore{

        return FirebaseFirestore.getInstance()

    }

    @Provides
    @Singleton

    fun amountrepo(): Wamntrepo{




    }


}