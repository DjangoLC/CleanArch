package com.example.cleanarchme.views.login

import com.example.cleanarchme.views.BasePresenter
import com.example.cleanarchme.views.BaseView

interface ContractLogin {

    interface LoginView : BaseView {
        fun setUser(user: String)
        fun setPassword(pass: String)
        fun isEnableFingerPrint(): Boolean
        fun emptyFields()
        fun errorFingerPrint()
        fun setupFingerPrint(visibility: Boolean, check: Boolean)

        fun nextActivity()
        fun loginError()
        fun errorBiometric(errString: String)
        fun loginBiometrics()
    }

    interface Presenter : BasePresenter<LoginView> {
        fun onLoginClick(user: String, pass: String)
        fun onEnableFingerPrintClick()
        fun biometricError(errString: String)
        fun biometricSuccess()
        fun biometricFailed()
        fun onLogin()
    }

}