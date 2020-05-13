package com.example.data.auth

interface Auth {

    fun authWithFingerPrint(title: String,
                            subtitle: String,
                            negativeButtonText: String = "Cancelar",
                            deviceCredentialAllowed: Boolean = false)

    enum class Status {
        LOGIN_SUCCESS,
        LOGIN_ERROR,
        AUTH_WITH_FINGER_PRINT,
        AUTH_WITH_FACE_ID
    }

}