package com.brainx.datasource.di

import com.brainx.datasource.local_pref.DatastorePrefManagerImp
import com.brainx.domain.pref_manager.DatastorePrefManager
import com.brainx.local_datastore.encryption.EncryptedDataStore
import org.koin.dsl.module

internal val datastorePrefManagerModule = module {
    single<DatastorePrefManager> {
        DatastorePrefManagerImp(
            encryptedDataStore = get<EncryptedDataStore>()
        )
    }
}