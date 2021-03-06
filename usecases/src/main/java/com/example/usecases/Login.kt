package com.example.usecases

import com.example.data.auth.Auth
import com.example.data.repository.user.UserRepository

class Login(private val usersRepository: UserRepository) {

    suspend fun invoke(user: String, pass: String) : Auth.Status {
        return usersRepository.login(user,pass)
    }

}