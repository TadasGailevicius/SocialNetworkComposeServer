package com.tedm.service

import com.tedm.data.repository.follow.FollowRepository
import com.tedm.data.requests.FollowUpdateRequest

class FollowService (
    private val repository: FollowRepository
) {
    suspend fun followUserIfExists(request: FollowUpdateRequest, followingUserId: String): Boolean {
        return repository.followUserIfExists(
            followingUserId = followingUserId,
            followedUserId = request.followedUserId
        )
    }

    suspend fun unfollowUserIfExists(request: FollowUpdateRequest, followingUserId: String): Boolean {
        return repository.unfollowUserIfExists(
            followingUserId = followingUserId,
            followedUserId = request.followedUserId
        )
    }

}