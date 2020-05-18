package com.example.usecases

import com.example.data.repository.user.UserRepository

class FingerPrintAuthenticate(private val usersRepository: UserRepository) {

    suspend operator fun invoke() = usersRepository.login("","")


}