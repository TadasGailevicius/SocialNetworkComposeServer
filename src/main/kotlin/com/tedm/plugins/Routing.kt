package com.tedm.plugins

import com.tedm.routes.*
import com.tedm.service.FollowService
import com.tedm.service.PostService
import com.tedm.service.UserService
import io.ktor.routing.*
import io.ktor.application.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    val userService: UserService by inject()

    val followService: FollowService by inject()

    val postService: PostService by inject()

    val jwtIssuer = environment.config.property("jwt.domain").getString()
    val jwtAudience = environment.config.property("jwt.audience").getString()
    val jwtSecret = environment.config.property("jwt.secret").getString()

    routing {
        // User routes
        createUserRoute(userService)
        loginUser(
            userService = userService,
            jwtIssuer = jwtIssuer,
            jwtAudience = jwtAudience,
            jwtSecret = jwtSecret,
        )

        // Following Routes
        followUser(followService)
        unfollowUser(followService)

        // Post routes
        createPostRoute(
            postService = postService,
            userService = userService
        )

    }
}
