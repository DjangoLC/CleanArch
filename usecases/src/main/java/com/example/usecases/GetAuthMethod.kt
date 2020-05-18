package com.example.usecases

import com.example.data.repository.user.UserRepository

class GetAuthMethod(private val usersRepository: UserRepository) {

    operator fun invoke() = usersRepository.getAuthMethod()

}