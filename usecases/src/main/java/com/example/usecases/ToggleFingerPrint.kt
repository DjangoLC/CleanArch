package com.example.usecases

import com.example.data.repository.UserRepository

class ToggleFingerPrint (private val userRepository: UserRepository) {

    fun invoke(value: Boolean) {
        userRepository.enableFingerPrint(value)
    }

}