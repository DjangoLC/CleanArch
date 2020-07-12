package com.example.cleanarchme.views.login

import android.content.Intent
import android.graphics.Outline
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewOutlineProvider
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricPrompt
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.example.cleanarchme.R
import com.example.cleanarchme.data.server.Movie
import com.example.cleanarchme.views.common.TweakableOutlineProvider
import com.example.cleanarchme.views.common.toast
import com.example.cleanarchme.views.main.MainActivity
import com.example.cleanarchme.views.main.MoviesAdapter
import com.example.cleanarchme.views.show
import com.example.data.auth.Auth
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.android.ext.android.inject
import org.koin.android.scope.lifecycleScope
import org.koin.core.parameter.parametersOf
import kotlin.math.roundToInt

class Activity : AppCompatActivity(), ContractLogin.View {

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

    private lateinit var outlineProvider: TweakableOutlineProvider

    private val mAdapter = SnapAdapter()

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        presenter.attach(this)
        presenter.setupView()
        btnLogin.setOnClickListener {
            //presenter.onLoginClick(tilUser.text.toString(),tilPass.text.toString())
        }

        checkFingerPrint.setOnCheckedChangeListener { _, isChecked ->
            presenter.onEnableFingerPrintClick(isChecked)
        }

        customSwitch.seClickTextOnListener {
            toast("result is: $it")
        }


        recyclerSnap.apply {
            layoutManager =
                LinearLayoutManager(this@Activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = mAdapter
            val snapHelper = LinearSnapHelper()
            snapHelper.attachToRecyclerView(this)
        }

        mAdapter.snaps = listOf(Snap(), Snap(),Snap(),Snap(),Snap(),Snap(),Snap(),Snap(),Snap(),Snap(),Snap())

    }


    private fun setScaleX(scaleXPercent: Int) {
        val scale = scaleXPercent - 200 / 2
        outlineProvider.scaleX = 1 + scale / 100f
    }


    private fun setScaleY(scaleYPercent: Int) {
        val scale = scaleYPercent - 200 / 2
        outlineProvider.scaleY = 1 + scale / 100f
    }


    private fun setShiftY(shiftYDp: Int) {
        val adjustedShiftYDp = shiftYDp - 200 / 2
        val adjustedShiftYPixel = adjustedShiftYDp * resources.displayMetrics.density
        outlineProvider.yShift = adjustedShiftYPixel.roundToInt()
    }

    override fun setUser(user: String) {
        //tilUser.setText(user)
    }

    override fun setPassword(pass: String) {
        tilPass.setText(pass)
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
