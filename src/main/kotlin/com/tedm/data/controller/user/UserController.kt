package com.tedm.data.controller.user

import com.tedm.data.models.User

interface UserController {

    suspend fun createUser(user: User)

    suspend fun getUserById(id: String) : User?

    suspend fun getUserByEmail(email: String): User?
}