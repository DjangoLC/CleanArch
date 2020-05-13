package com.example.cleanarchme.views.login

import com.example.cleanarchme.views.BasePresenter

interface ContractLogin {

    interface ContractLoginView {
        fun getUser(): String
        fun getPassword(): String
        fun isEnableFingerPrint() : Boolean
        fun emptyFields()
        fun errorFingerPrint()

        fun nextActivity()
        fun loginError()
        fun errorBiometric()
        fun loginBiometrics()
    }

    interface ContractPresenter : BasePresenter<ContractLoginView> {
        fun onLoginClick()
        fun onEnableFingerPrintClick()
    }

}