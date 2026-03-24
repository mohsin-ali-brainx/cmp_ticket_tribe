package com.brainx.local_datastore.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.brainx.local_datastore.encryption.createEncryptedDataStore
import com.brainx.local_datastore.encryption.EncryptedDataStore
import com.brainx.local_datastore.setup.createDataStore
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.Module
import org.koin.dsl.module

internal actual val datastorePlatformModule: Module
    get() = module {
        single<DataStore<Preferences>> { createDataStore(androidApplication(), get()) }
        single<EncryptedDataStore> {
            createEncryptedDataStore(
                dataStore = get(),
                datastoreFileProvider = get()
            )
        }
    }
