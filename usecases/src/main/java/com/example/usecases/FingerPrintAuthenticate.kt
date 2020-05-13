package com.example.usecases

import com.example.data.repository.UserRepository

class FingerPrintAuthenticate(private val userRepository: UserRepository) {

    suspend operator fun invoke() = userRepository.login("","")


}