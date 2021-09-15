package com.tedm.di

import com.tedm.data.controller.user.UserRepository
import com.tedm.data.controller.user.UserRepositoryImpl
import com.tedm.util.Constants.DATABASE_NAME
import org.koin.dsl.module
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

val mainModule = module {
    single{
        val client = KMongo.createClient().coroutine
        client.getDatabase(DATABASE_NAME)
    }
    single <UserRepository>{
        UserRepositoryImpl(get())
    }
}