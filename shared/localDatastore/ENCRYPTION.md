# EncryptedDataStore — Encryption Guide

This document describes how encryption works in the `localDatastore` KMP module and how to use `EncryptedDataStore` for secure storage on Android and iOS.

## Overview

`EncryptedDataStore` is an encrypted wrapper around `DataStore<Preferences>`. All values are encrypted with **AES-256-GCM** before being written to disk. Encryption keys are stored in platform-specific secure storage:

| Platform | Key Storage | Notes |
|----------|-------------|-------|
| **Android** | Android Keystore | Hardware-backed when available, non-exportable |
| **iOS** | Keychain | Protected by device passcode, not backed up to iCloud |

## How Encryption Works

### Algorithm

- **Cipher:** AES-256-GCM (authenticated encryption)
- **Key size:** 256 bits
- **IV/Nonce:** 12 bytes, randomly generated per encryption
- **Format:** IV (12 bytes) || ciphertext (or nonce || ciphertext depending on platform)

### Data Flow

**Write (Put):**
```
Value (T) → JSON serialize → UTF-8 bytes → Encrypt (AES-GCM) → Base64 → DataStore
```

**Read (Get):**
```
DataStore → Base64 string → Decode → Decrypt (AES-GCM) → UTF-8 bytes → JSON deserialize → Value (T)
```

### Key Management

- Each preference key has a corresponding encryption key stored in secure storage
- Key identifier format: `{keyPrefix}.{datastoreName}.{preferenceKey}`
- Example: `com.brainx.local_datastore.my_datastore.access_token`
- Keys are automatically deleted when `delete(key)` is called

## Usage

### 1. Dependency Injection (Koin)

`EncryptedDataStore` is automatically provided when you include `datastoreModuleProvider`:

```kotlin
// In your app's Koin setup
startKoin {
    modules(
        datastoreModuleProvider,
        // ... other modules
    )
}
```

Ensure `DatastoreFileProvider` is also provided (required for DataStore creation and EncryptedDataStore):

```kotlin
single<DatastoreFileProvider> { MyDatastoreFileProvider() }
```

### 2. Inject and Use

```kotlin
class MyRepository(
    private val encryptedDataStore: EncryptedDataStore
) {
    suspend fun saveToken(token: String) {
        encryptedDataStore.put("auth_token", token)
    }

    suspend fun getToken(): String {
        return encryptedDataStore.get("auth_token", "")
    }

    fun observeToken(): Flow<String> {
        return encryptedDataStore.getFlow("auth_token", "")
    }

    suspend fun clearToken() {
        encryptedDataStore.delete("auth_token")
    }
}
```

### 3. Serializable Types

For custom types, use `@Serializable`:

```kotlin
@Serializable
data class UserPreferences(
    val theme: String,
    val notificationsEnabled: Boolean
)

// Usage
encryptedDataStore.put("user_prefs", UserPreferences("dark", true))
val prefs = encryptedDataStore.get("user_prefs", UserPreferences("light", false))
```

### 4. With Explicit Serializer

```kotlin
encryptedDataStore.put(
    key = "my_key",
    value = myObject,
    serializer = MyObject.serializer()
)
val value = encryptedDataStore.get(
    key = "my_key",
    defaultValue = defaultObject,
    serializer = MyObject.serializer()
)
```

## API Reference

| Method | Description |
|--------|-------------|
| `get(key, defaultValue)` | Retrieve and decrypt a value (suspend) |
| `put(key, value)` | Encrypt and store a value (suspend) |
| `getFlow(key, defaultValue)` | Observe an encrypted value as a Flow |
| `delete(key)` | Remove value and its encryption key (suspend) |
| `innerDataStore` | Access underlying DataStore for unencrypted operations |

## When to Use

- **Use EncryptedDataStore** for sensitive data: tokens, passwords, PII, API keys
- **Use DataStore directly** for non-sensitive data: UI preferences, feature flags, cached IDs

You can inject both and use each where appropriate:

```kotlin
single<DataStore<Preferences>> { /* ... */ }
single<EncryptedDataStore> { /* ... */ }
```

## Security Considerations

1. **Device lock:** On Android, keys may be inaccessible when the device is locked (depending on Keystore configuration). On iOS, Keychain uses `kSecAttrAccessibleAfterFirstUnlockThisDeviceOnly`.

2. **App uninstall:** Encryption keys are removed when the app is uninstalled (Android Keystore, iOS Keychain).

3. **Backup:** Keys are not included in Android backups or iCloud Keychain backup (`ThisDeviceOnly` on iOS).

4. **Tampering:** AES-GCM provides authentication; tampered ciphertext will fail to decrypt.

## Troubleshooting

| Error | Cause | Solution |
|-------|-------|----------|
| "Cannot access Keystore - device may be locked" | Device locked (Android) | Ensure device is unlocked before accessing |
| "Cannot access Keychain - device is locked" | Device locked (iOS) | Same as above |
| "No encryption key found" | Key was deleted or app reinstalled | Data cannot be recovered; handle gracefully |
| Returns `defaultValue` on get | Decryption failed (wrong key, corrupted data) | Check for app reinstall or data migration |

## Manual Creation

If you need to create `EncryptedDataStore` without DI:

```kotlin
val encryptedDataStore = createEncryptedDataStore(
    dataStore = myDataStore,
    datastoreFileProvider = myDatastoreFileProvider,
    keyPrefix = "com.brainx.local_datastore"  // optional
)
```
