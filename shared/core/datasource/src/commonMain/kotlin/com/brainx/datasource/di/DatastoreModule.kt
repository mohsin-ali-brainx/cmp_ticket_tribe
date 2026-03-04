package com.brainx.datasource.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.brainx.datasource.local_pref.DatastorePrefManagerImp
import com.brainx.domain.pref_manager.DatastorePrefManager
import org.koin.dsl.module

internal val datastorePrefManagerModule = module {
    single<DatastorePrefManager> {
        DatastorePrefManagerImp(
            datastorePreference = get<DataStore<Preferences>>()
        ) as DatastorePrefManager
    }
}