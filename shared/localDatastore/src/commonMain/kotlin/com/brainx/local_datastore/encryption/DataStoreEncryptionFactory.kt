package com.brainx.local_datastore.encryption

/**
 * Factory for creating platform-specific [DataStoreEncryption] instances.
 *
 * @param keyPrefix Prefix used for key identifiers (e.g. "com.brainx.local_datastore")
 * @param datastoreName Name of the DataStore (from [DatastoreFileProvider]) - used in key aliases
 */
internal expect fun createDataStoreEncryption(
    keyPrefix: String,
    datastoreName: String
): DataStoreEncryption
