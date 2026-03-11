package com.brainx.datasource.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.brainx.datasource.local_pref.DatastorePrefManagerImp
import com.brainx.datasource.security.PlatformSecureValueCipher
import com.brainx.datasource.security.SecureValueCipher
import com.brainx.domain.pref_manager.DatastorePrefManager
import org.koin.dsl.module

internal val datastorePrefManagerModule = module {
    single<SecureValueCipher> { PlatformSecureValueCipher() }

    single<DatastorePrefManager> {
        DatastorePrefManagerImp(
            datastorePreference = get<DataStore<Preferences>>(),
            secureValueCipher = get<SecureValueCipher>()
        ) as DatastorePrefManager
    }
}