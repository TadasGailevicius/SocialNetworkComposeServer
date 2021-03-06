package com.tedm.plugins

import com.tedm.routes.*
import com.tedm.service.*
import io.ktor.routing.*
import io.ktor.application.*
import io.ktor.http.content.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    val userService: UserService by inject()

    val followService: FollowService by inject()

    val postService: PostService by inject()

    val likeService: LikeService by inject()

    val commentService: CommentService by inject()

    val activityService: ActivityService by inject()

    val jwtIssuer = environment.config.property("jwt.domain").getString()
    val jwtAudience = environment.config.property("jwt.audience").getString()
    val jwtSecret = environment.config.property("jwt.secret").getString()

    routing {
        // User routes
        createUser(userService = userService)
        loginUser(
            userService = userService,
            jwtIssuer = jwtIssuer,
            jwtAudience = jwtAudience,
            jwtSecret = jwtSecret,
        )
        searchUser(userService = userService)
        getUserProfile(userService = userService)
        getPostsForProfile(postService = postService)
        updateUserProfile(userService = userService)

        // Following Routes
        followUser(
            followService = followService,
            activityService = activityService
        )
        unfollowUser(followService = followService)

        // Post routes
        createPost(
            postService = postService
        )
        getPostsForFollows(
            postService = postService
        )
        deletePost(
            postService = postService,
            likeService = likeService,
            commentService = commentService
        )

        //Like routes
        likeParent(
            likeService = likeService,
            activityService = activityService
        )
        unlikeParent(
            likeService = likeService
        )

        getLikesForParent(
            likeService = likeService
        )

        // Comment routes
        createComment(
            commentService = commentService,
            activityService = activityService
        )

        deleteComment(
            commentService = commentService,
            likeService = likeService
        )

        getCommentsForPost(
            commentService = commentService
        )
        // Activity routes
        getActivities(activityService = activityService)

        static {
            resources("static")
        }


    }
}
