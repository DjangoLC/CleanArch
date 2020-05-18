package com.example.cleanarchme.views.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricPrompt
import com.example.cleanarchme.R
import com.example.cleanarchme.views.common.toast
import com.example.cleanarchme.views.main.MainActivity
import com.example.cleanarchme.views.show
import com.example.data.auth.Auth
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.android.ext.android.inject
import org.koin.android.scope.lifecycleScope
import org.koin.core.parameter.parametersOf

class LoginActivity : AppCompatActivity(), ContractLogin.LoginView {

    private val presenter: ContractLogin.Presenter by lifecycleScope.inject()

    private val auth: Auth by inject {
        parametersOf(this, callback)
    }

    private val callback = object : BiometricPrompt.AuthenticationCallback() {
        override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
            super.onAuthenticationError(errorCode, errString)
            presenter.biometricError(errString.toString())
        }

        override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
            super.onAuthenticationSucceeded(result)
            presenter.biometricSuccess()
        }

        override fun onAuthenticationFailed() {
            super.onAuthenticationFailed()
            presenter.biometricFailed()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        presenter.attach(this)

        btnLogin.setOnClickListener {
            presenter.onLoginClick()
        }

        checkFingerPrint.setOnCheckedChangeListener { buttonView, isChecked ->
            presenter.onEnableFingerPrintClick()
        }

        presenter.onLogin()
    }

    override fun onDestroy() {
        presenter.detach()
        super.onDestroy()
    }

    override fun getUser(): String {
        return tilUser.text.toString()
    }

    override fun getPassword(): String {
        return tilPass.text.toString()
    }

    override fun setUser(user: String) {
        tilUser.setText(user)
    }

    override fun setPassword(pass: String) {
        tilPass.setText(pass)
    }

    override fun isEnableFingerPrint(): Boolean {
        return checkFingerPrint.isChecked
    }

    override fun emptyFields() {
        Toast.makeText(this, getString(R.string.empty_fields), Toast.LENGTH_SHORT).show()
    }

    override fun errorFingerPrint() {
        Toast.makeText(this, getString(R.string.error_fingerprint), Toast.LENGTH_SHORT).show()
    }

    override fun setupFingerPrint(visibility: Boolean, check: Boolean) {
        checkFingerPrint.show(visibility)
        checkFingerPrint.isChecked = check
    }

    override fun nextActivity() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun loginError() {
        toast(getString(R.string.login_error))
    }

    override fun errorBiometric(errString: String) {
        toast(errString)
    }

    override fun loginBiometrics() {
        auth.authWithFingerPrint("title", "subtitle", deviceCredentialAllowed = true)
    }
}
