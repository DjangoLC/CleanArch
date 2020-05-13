package com.example.usecases

import com.example.data.repository.UserRepository
import com.example.domain.User

class GetUser(private val userRepository: UserRepository) {

    fun invoke()  = userRepository.getUser()

}