package com.example.cleanarchme.data

import androidx.biometric.BiometricPrompt
import androidx.fragment.app.FragmentActivity
import com.example.data.auth.Auth
import java.util.concurrent.Executor

class AuthImpl(
    private var activity: FragmentActivity,
    private val callbackPrompt: BiometricPrompt.AuthenticationCallback
) : Auth {

    private val executor = Executor { r -> r.run() }

    override fun authWithFingerPrint(title: String, subtitle: String, negativeButtonText: String, deviceCredentialAllowed: Boolean) {
        val promptInfo =
            createBiometricPrompt(title, subtitle, negativeButtonText, deviceCredentialAllowed)

        val biometricPrompt = BiometricPrompt(activity, executor, callbackPrompt)

        biometricPrompt.authenticate(promptInfo)
    }

    private fun createBiometricPrompt(
        title: String,
        subtitle: String,
        negativeButtonText: String,
        deviceCredentialAllowed: Boolean
    ): BiometricPrompt.PromptInfo {

        return if (deviceCredentialAllowed) {
            BiometricPrompt.PromptInfo.Builder()
                .setTitle(title)
                .setSubtitle(subtitle)
                .setDeviceCredentialAllowed(deviceCredentialAllowed)
                .setConfirmationRequired(false)
                .build()
        } else {
            BiometricPrompt.PromptInfo.Builder()
                .setTitle(title)
                .setSubtitle(subtitle)
                .setNegativeButtonText(negativeButtonText)
                .build()
        }
    }

    override fun authWithFaceID(callback: (Auth.Status) -> Unit) {
        callback(Auth.Status.NO_AUTH)
    }
}