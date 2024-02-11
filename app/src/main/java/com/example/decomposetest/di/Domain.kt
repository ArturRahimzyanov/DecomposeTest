package com.example.decomposetest.di

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.dsl.module



val domainKoinModule =  module {
    single { CoroutineScope(SupervisorJob() + Dispatchers.IO) }
}