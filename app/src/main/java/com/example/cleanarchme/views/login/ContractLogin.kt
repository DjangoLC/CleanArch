package com.example.cleanarchme.views.login

import com.example.cleanarchme.views.BasePresenter
import com.example.cleanarchme.views.BaseView

interface ContractLogin {

    interface View : BaseView {
        fun setUser(user: String)
        fun setPassword(pass: String)
        fun emptyFields()
        fun errorFingerPrint()
        fun setupFingerPrint(visibility: Boolean, check: Boolean)

        fun nextActivity()
        fun loginError()
        fun errorBiometric(errString: String)
        fun loginBiometrics()
    }

    interface Presenter : BasePresenter<View> {
        fun onLoginClick(user: String, pass: String)
        fun onEnableFingerPrintClick(boolean: Boolean)
        fun biometricError(errString: String)
        fun biometricSuccess()
        fun biometricFailed()
        fun onLogin()
        fun setupView()
    }

}