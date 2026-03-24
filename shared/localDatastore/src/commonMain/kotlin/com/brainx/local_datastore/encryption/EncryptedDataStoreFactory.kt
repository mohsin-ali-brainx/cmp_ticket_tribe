package com.brainx.local_datastore.encryption

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.brainx.local_datastore.provider.DatastoreFileProvider

/**
 * Creates an [EncryptedDataStore] wrapping the given [DataStore] with platform encryption.
 *
 * @param dataStore The underlying DataStore
 * @param datastoreFileProvider Provides the DataStore file name (used for key aliases)
 * @param keyPrefix Optional prefix for key identifiers. Default: "com.brainx.local_datastore"
 */
fun createEncryptedDataStore(
    dataStore: DataStore<Preferences>,
    datastoreFileProvider: DatastoreFileProvider,
    keyPrefix: String = "com.brainx.local_datastore"
): EncryptedDataStore {
    val datastoreName = datastoreFileProvider.getDatastoreName()
        ?: throw IllegalArgumentException("Datastore name must not be null for EncryptedDataStore")
    val encryption = createDataStoreEncryption(keyPrefix, datastoreName)
    return EncryptedDataStore(
        dataStore = dataStore,
        encryption = encryption,
        keyPrefix = keyPrefix,
        datastoreName = datastoreName
    )
}
