package com.tedm.data.repository.follow

import com.tedm.data.models.Following

interface FollowRepository {

    suspend fun followUserIfExists(
        followingUserId: String,
        followedUserId: String
    ): Boolean

    suspend fun unfollowUserIfExists(
        followingUserId: String,
        followedUserId: String
    ) : Boolean

    suspend fun getFollowsByUser(userId: String): List<Following>

    suspend fun doesUserFollow(followingUserId: String, followedUserId: String): Boolean

}