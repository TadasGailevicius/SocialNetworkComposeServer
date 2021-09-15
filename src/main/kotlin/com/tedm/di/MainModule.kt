package com.tedm.di

import com.tedm.data.controller.user.UserController
import com.tedm.data.controller.user.UserControllerImpl
import com.tedm.util.Constants.DATABASE_NAME
import org.koin.dsl.module
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

val mainModule = module {
    single{
        val client = KMongo.createClient().coroutine
        client.getDatabase(DATABASE_NAME)
    }
    single <UserController>{
        UserControllerImpl(get())
    }
}