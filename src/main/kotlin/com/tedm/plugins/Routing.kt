package com.tedm.plugins

import com.tedm.data.repository.user.UserRepository
import com.tedm.routes.createUserRoute
import com.tedm.routes.loginUser
import io.ktor.routing.*
import io.ktor.application.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    val userRepository: UserRepository by inject()
    routing {
        createUserRoute(userRepository)
        loginUser(userRepository)
    }
}
