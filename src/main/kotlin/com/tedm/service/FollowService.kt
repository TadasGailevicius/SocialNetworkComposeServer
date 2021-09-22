package com.tedm.service

import com.tedm.data.repository.follow.FollowRepository
import com.tedm.data.requests.FollowUpdateRequest

class FollowService (
    private val repository: FollowRepository
) {
    suspend fun followUserIfExists(request: FollowUpdateRequest): Boolean {
        return repository.followUserIfExists(
            followingUserId = request.followingUserId,
            followedUserId = request.followedUserId
        )
    }

    suspend fun unfollowUserIfExists(request: FollowUpdateRequest): Boolean {
        return repository.unfollowUserIfExists(
            followingUserId = request.followingUserId,
            followedUserId = request.followedUserId
        )
    }

}