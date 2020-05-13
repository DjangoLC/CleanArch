package com.example.data

interface UserPreferences {

    companion object {
        const val HAS_FINGERPRINT_ENABLE = "HAS_FINGERPRINT_ENABLE"
        const val HAS_FACE_ID_ENABLE = "HAS_FACE_ID_ENABLE"
        const val USER_PASS = "USER_PASS"
        const val USER_USERNAME = "USER_USERNAME"
        const val PREFERENCES = "com.example.data:PREFERENCES"
    }

    fun getBoolean(key: String): Boolean

    fun getString(key: String): String

    fun saveBoolean(key: String, boolean: Boolean)

    fun saveString(key: String, string: String)

}