package com.example.cleanarchme.data

import android.app.Application
import android.widget.Toast
import androidx.biometric.BiometricPrompt
import androidx.fragment.app.FragmentActivity
import com.example.data.auth.Auth
import java.util.concurrent.Executor
import kotlin.coroutines.resume

class AuthImpl(private val context: Application, private var activity: FragmentActivity) : Auth {

    private val executor = Executor { r -> r.run() }

    override fun authWithFingerPrint(
        title: String,
        subtitle: String,
        negativeButtonText: String,
        deviceCredentialAllowed: Boolean,
        callback: (Auth.Status) -> Unit
    ) {
        val promptInfo =
            createBiometricPrompt(title, subtitle, negativeButtonText, deviceCredentialAllowed)

        val biometricPrompt = BiometricPrompt(
            activity, executor, object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    Toast.makeText(context, "Authentication error: $errString", Toast.LENGTH_SHORT)
                        .show()
                    callback(Auth.Status.BIOMETRIC_FAILED)
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    callback(Auth.Status.BIOMETRIC_SUCCESS)
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    callback(Auth.Status.BIOMETRIC_FAILED)
                }
            })

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