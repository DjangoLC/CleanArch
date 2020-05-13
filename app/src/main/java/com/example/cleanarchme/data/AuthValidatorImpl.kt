package com.example.cleanarchme.data

import android.app.Application
import androidx.biometric.BiometricManager
import com.example.data.auth.AuthValidator

class AuthValidatorImpl(context: Application) : AuthValidator {

    private val biometricManager = BiometricManager.from(context)

    override fun hasFingerPrint(): Boolean {
        return biometricManager.canAuthenticate() == BiometricManager.BIOMETRIC_SUCCESS
    }

    override fun hasFaceId(): Boolean {
        return biometricManager.canAuthenticate() == BiometricManager.BIOMETRIC_SUCCESS
    }
}