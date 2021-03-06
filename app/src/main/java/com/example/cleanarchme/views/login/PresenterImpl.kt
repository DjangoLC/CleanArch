package com.example.cleanarchme.views.login

import com.example.cleanarchme.views.BasePresenterImpl
import com.example.data.auth.Auth
import com.example.data.auth.AuthMethod
import com.example.usecases.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class PresenterImpl(
    private val login: Login,
    private val getUser: GetUser,
    private val toggleFingerPrint: ToggleFingerPrint,
    private val getAuthMethod: GetAuthMethod,
    private val supportBiometrics: SupportBiometrics,
    uiDispatcher: CoroutineDispatcher
) : BasePresenterImpl<ContractLogin.View>(uiDispatcher), ContractLogin.Presenter{

    override fun onLoginClick(user: String, pass: String) {
        launch {

            if (user.isEmpty() || pass.isEmpty()) {
                view.emptyFields()
                return@launch
            }

            when (login.invoke(user, pass)) {
                Auth.Status.LOGIN_SUCCESS -> {
                    view.nextActivity()
                }
                Auth.Status.LOGIN_ERROR -> {
                    view.loginError()
                }
                Auth.Status.AUTH_WITH_FINGER_PRINT -> {
                    view.loginBiometrics()
                }
                Auth.Status.AUTH_WITH_FACE_ID -> {
                    view.loginBiometrics()
                }
            }
        }
    }

    override fun onEnableFingerPrintClick(boolean: Boolean) {
        toggleFingerPrint.invoke(boolean)
    }

    override fun biometricError(errString: String) {
        view.errorBiometric(errString)
    }

    override fun biometricSuccess() {
        view.nextActivity()
    }

    override fun biometricFailed() {

    }

    override fun onLogin() {
        val authMethod = getAuthMethod.invoke()

        if (authMethod == AuthMethod.BIOMETRIC) {
            view.loginBiometrics()
        }
    }

    override fun setupView() {
        val user = getUser.invoke()
        val authMethod = getAuthMethod.invoke()
        val supportBiometrics = supportBiometrics.invoke()

        view.setUser(user.username)
        view.setPassword(user.password)
        view.setupFingerPrint(supportBiometrics, authMethod == AuthMethod.BIOMETRIC)

        if (authMethod == AuthMethod.BIOMETRIC) onLogin()
    }

}