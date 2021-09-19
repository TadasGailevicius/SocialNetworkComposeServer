package com.tedm.data.requests

data class FollowUpdateRequest (
    val followingUserId: String,
    val followedUserId: String
)