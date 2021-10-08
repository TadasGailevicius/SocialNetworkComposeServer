package com.tedm.plugins

import com.tedm.routes.*
import com.tedm.service.*
import io.ktor.routing.*
import io.ktor.application.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    val userService: UserService by inject()

    val followService: FollowService by inject()

    val postService: PostService by inject()

    val likeService: LikeService by inject()

    val commentService: CommentService by inject()

    val jwtIssuer = environment.config.property("jwt.domain").getString()
    val jwtAudience = environment.config.property("jwt.audience").getString()
    val jwtSecret = environment.config.property("jwt.secret").getString()

    routing {
        // User routes
        createUser(userService)
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
        createPost(
            postService = postService,
            userService = userService
        )
        getPostsForFollows(
            postService = postService
        )
        deletePost(
            postService = postService,
            likeService = likeService
        )

        //Like routes
        likeParent(
            likeService = likeService
        )
        unlikeParent(
            likeService = likeService
        )

        // Comment routes

        createComment(
            commentService = commentService
        )

        deleteComment(
            commentService = commentService,
            likeService = likeService
        )

        getCommentsForPost(
            commentService = commentService
        )


    }
}
