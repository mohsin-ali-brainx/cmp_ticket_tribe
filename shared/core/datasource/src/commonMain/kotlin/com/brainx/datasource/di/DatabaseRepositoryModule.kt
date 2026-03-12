package com.brainx.datasource.di

import com.brainx.datasource.local_database.repository_imp.UserDbRepositoryImp
import com.brainx.domain.db.repository.LocalDbUserRepository
import org.koin.dsl.module

val databaseRepositoryModule = module {
    single<LocalDbUserRepository> {
        UserDbRepositoryImp(
          get()
        ) as LocalDbUserRepository
    }
}