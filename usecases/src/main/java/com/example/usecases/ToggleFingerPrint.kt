package com.example.usecases

import com.example.data.repository.user.UserRepository

class ToggleFingerPrint (private val usersRepository: UserRepository) {

    fun invoke(value: Boolean) {
        usersRepository.enableFingerPrint(value)
    }

}