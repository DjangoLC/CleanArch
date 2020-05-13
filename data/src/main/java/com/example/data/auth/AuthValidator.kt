package com.example.data.auth

interface AuthValidator {

    fun hasFingerPrint() : Boolean

    fun hasFaceId() : Boolean

}