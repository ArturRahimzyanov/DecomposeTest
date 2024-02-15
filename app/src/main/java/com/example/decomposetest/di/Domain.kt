package com.example.decomposetest.di

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module


val domainKoinModule =  module {
    single { CoroutineScope(Dispatchers.IO) }
}