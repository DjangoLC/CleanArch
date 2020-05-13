package com.example.cleanarchme.views.login

import com.example.cleanarchme.views.common.Scope
import com.example.data.auth.Auth
import com.example.data.auth.AuthMethod
import com.example.usecases.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class ContractPresenterImpl(
    private val login: Login,
    private val getUser: GetUser,
    private val toggleFingerPrint: ToggleFingerPrint,
    private val getAuthMethod: GetAuthMethod,
    private val supportBiometrics: SupportBiometrics,
    uiDispatcher: CoroutineDispatcher
) : ContractLogin.ContractPresenter, Scope by Scope.Impl(uiDispatcher) {

    private var view: ContractLogin.ContractLoginView? = null

    init {
        initScope()
    }

    override fun attach(view: ContractLogin.ContractLoginView) {
        this.view = view
        setupView()
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

    override fun onLogin() {
        val authMethod = getAuthMethod.invoke()

        if (authMethod == AuthMethod.BIOMETRIC) {
            view?.loginBiometrics()
        }
    }

    private fun setupView() {
        val user = getUser.invoke()
        val authMethod = getAuthMethod.invoke()
        val biometrics = supportBiometrics.invoke()

        view?.setUser(user.username)
        view?.setPassword(user.password)
        view?.setupFingerPrint(biometrics, authMethod == AuthMethod.BIOMETRIC)
    }

}