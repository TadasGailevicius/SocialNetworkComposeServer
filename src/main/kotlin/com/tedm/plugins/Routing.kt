package com.tedm.plugins

import com.tedm.data.repository.follow.FollowRepository
import com.tedm.data.repository.user.UserRepository
import com.tedm.routes.createUserRoute
import com.tedm.routes.followUser
import com.tedm.routes.loginUser
import com.tedm.routes.unfollowUser
import io.ktor.routing.*
import io.ktor.application.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    val userRepository: UserRepository by inject()
    val followRepository: FollowRepository by inject()
    routing {
        // User routes
        createUserRoute(userRepository)
        loginUser(userRepository)
        // Following Routes
        followUser(followRepository)
        unfollowUser(followRepository)

    }
}
