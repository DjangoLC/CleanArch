package com.example.usecases

import com.example.data.repository.UserRepository

class SupportBiometrics(private val userRepository: UserRepository) {

    fun invoke() = userRepository.getSupportBiometrics()

}