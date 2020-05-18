package com.example.data.repository.user

import com.example.data.auth.Auth
import com.example.data.auth.AuthMethod
import com.example.domain.User

interface UserRepository {
    suspend fun login(user: String, pass: String): Auth.Status
    fun enableFingerPrint(value: Boolean)
    fun getUser(): User
    fun getAuthMethod(): AuthMethod
    fun getSupportBiometrics(): Boolean
}