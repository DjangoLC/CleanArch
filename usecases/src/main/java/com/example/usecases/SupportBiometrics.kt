package com.example.usecases

import com.example.data.repository.user.UserRepository

class SupportBiometrics(private val usersRepository: UserRepository) {

    fun invoke() = usersRepository.getSupportBiometrics()

}