package com.example.cleanarchme.views.login

import com.example.cleanarchme.views.common.Scope
import com.example.data.auth.Auth
import com.example.usecases.Login
import com.example.usecases.ToggleFingerPrint
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class ContractPresenterImpl(
    private val login: Login,
    private val toggleFingerPrint: ToggleFingerPrint,
    uiDispatcher: CoroutineDispatcher
) : ContractLogin.ContractPresenter, Scope by Scope.Impl(uiDispatcher) {

    private var view: ContractLogin.ContractLoginView? = null

    init {
        initScope()
    }

    override fun attach(view: ContractLogin.ContractLoginView) {
        this.view = view
    }

    override fun detach() {
        this.view = null
        destroyScope()
    }

    override fun onLoginClick() {
        launch {
            val user = view?.getUser() ?: ""
            val pass = view?.getPassword() ?: ""

            if (user.isEmpty() || pass.isEmpty()) {
                view?.emptyFields()
                return@launch
            }

            when (login.invoke(user, pass)) {
                Auth.Status.LOGIN_SUCCESS -> {
                    view?.nextActivity()
                }
                Auth.Status.NO_AUTH -> {

                }
                Auth.Status.BIOMETRIC_SUCCESS -> {
                    view?.nextActivity()
                }
                Auth.Status.BIOMETRIC_FAILED -> {

                }
                Auth.Status.LOGIN_ERROR -> {
                    view?.loginError()
                }
                Auth.Status.AUTH_WITH_FINGER_PRINT -> {
                    view?.loginBiometrics()
                }
                Auth.Status.AUTH_WITH_FACE_ID -> {
                    view?.loginBiometrics()
                }
            }
        }
    }

    override fun onEnableFingerPrintClick() {
        toggleFingerPrint.invoke(view?.isEnableFingerPrint() ?: false)
    }

    override fun biometricError(errString: String) {
        view?.errorBiometric(errString)
    }

    override fun biometricSuccess() {
        view?.nextActivity()
    }

    override fun biometricFailed() {

    }
}