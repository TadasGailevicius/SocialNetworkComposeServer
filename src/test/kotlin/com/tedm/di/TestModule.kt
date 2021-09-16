package com.tedm.di

import com.tedm.data.repository.FakeUserRepository
import org.koin.dsl.module

internal val testModule = module {
    single {
        FakeUserRepository()
    }
}