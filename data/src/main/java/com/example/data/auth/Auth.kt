package com.example.data.auth

interface Auth {

    fun authWithFingerPrint(title: String,
                            subtitle: String,
                            negativeButtonText: String = "Cancelar",
                            deviceCredentialAllowed: Boolean = false)

    fun authWithFaceID(callback: (Auth.Status) -> Unit)

    enum class Status {
        NO_AUTH,
        BIOMETRIC_SUCCESS,
        BIOMETRIC_FAILED,
        LOGIN_SUCCESS,
        LOGIN_ERROR,
        AUTH_WITH_FINGER_PRINT,
        AUTH_WITH_FACE_ID
    }

}