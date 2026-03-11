package com.brainx.datasource.di

import com.brainx.datasource.network.respository_imp.AuthRepositoryImp
import com.brainx.domain.network.repository.AuthRepository
import com.brainx.ktor_network.utils.enums.ApiKeysModuleEnums
import com.brainx.ktor_network.utils.enums.NetworkModuleEnums
import org.koin.core.qualifier.named
import org.koin.dsl.module

val repositoryModule = module {
    single<AuthRepository> {
        AuthRepositoryImp(
            httpClient = get(named(NetworkModuleEnums.DEFAULT_CLIENT.type)),
        ) as AuthRepository
    }
}