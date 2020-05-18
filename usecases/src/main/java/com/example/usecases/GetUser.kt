package com.example.usecases

import com.example.data.repository.user.UserRepository

class GetUser(private val usersRepository: UserRepository) {

    fun invoke()  = usersRepository.getUser()

}