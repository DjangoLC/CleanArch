package com.example.data.repository

import com.example.data.UserPreferences
import com.example.data.auth.Auth
import com.example.data.auth.AuthValidator
import com.example.data.source.LocalDataSource

class UserRepository(
    private val authValidator: AuthValidator,
    private val preferences: UserPreferences,
    private val localDataSource: LocalDataSource
) {

    suspend fun login(user: String, pass: String): Auth.Status {

        preferences.saveString(UserPreferences.USER_PASS, pass)
        preferences.saveString(UserPreferences.USER_USERNAME, user)

        return when {
            preferences.getBoolean(UserPreferences.HAS_FINGERPRINT_ENABLE) -> {
                Auth.Status.AUTH_WITH_FINGER_PRINT
            }
            preferences.getBoolean(UserPreferences.HAS_FINGERPRINT_ENABLE) -> {
                Auth.Status.AUTH_WITH_FACE_ID
            }
            else -> {
                localDataSource.login(user, pass)
            }
        }
    }

    fun enableFingerPrint(value: Boolean) {
        preferences.saveBoolean(UserPreferences.HAS_FINGERPRINT_ENABLE, value)
    }
}