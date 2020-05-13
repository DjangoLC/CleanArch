package com.example.usecases

import com.example.data.repository.UserRepository

class GetAuthMethod(private val userRepository: UserRepository) {

    operator fun invoke() = userRepository.getAuthMethod()

}