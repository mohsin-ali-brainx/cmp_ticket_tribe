package com.brainx.domain.di

import com.brainx.domain.use_cases.LoginUseCase
import org.koin.dsl.module

internal val useCaseModule = module {
    single {
        LoginUseCase(
            authRepository = get(),
            datastore = get(),
            userDbRepository = get()
        )
    }
}