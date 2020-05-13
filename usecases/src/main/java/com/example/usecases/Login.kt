package com.example.usecases

import com.example.data.auth.Auth
import com.example.data.repository.UserRepository

class Login(private val userRepository: UserRepository) {

    suspend fun invoke(user: String, pass: String) : Auth.Status {
        return userRepository.login(user,pass)
    }

}