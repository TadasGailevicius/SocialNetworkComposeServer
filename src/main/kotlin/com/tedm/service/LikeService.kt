package com.tedm.service

import com.tedm.data.repository.follow.FollowRepository
import com.tedm.data.repository.likes.LikeRepository
import com.tedm.data.repository.user.UserRepository
import com.tedm.data.responses.UserResponseItem

class LikeService(
    private val likeRepository: LikeRepository,
    private val userRepository: UserRepository,
    private val followRepository: FollowRepository
) {

    suspend fun likeParent(userId: String, parentId: String, parentType: Int): Boolean {
        return likeRepository.likeParent(
            userId = userId,
            parentId = parentId,
            parentType = parentType
        )
    }

    suspend fun unlikeParent(userId: String, parentId: String): Boolean {
        return likeRepository.unlikeParent(
            userId = userId,
            parentId = parentId
        )
    }

    suspend fun deleteLikesForParent(parentId: String) {
        return likeRepository.deleteLikesForParent(
            parentId = parentId
        )
    }

    suspend fun getUsersWhoLikedParent(parentId: String, userId: String): List<UserResponseItem> {
        val userIds = likeRepository.getLikesForParent(parentId).map { it.userId }
        val users = userRepository.getUsers(userIds)
        val followsByUser = followRepository.getFollowsByUser(userId)
        return users.map { user ->
            val isFollowing = followsByUser.find { it.followedUserId == user.id } != null
            UserResponseItem(
                userId = user.id,
                username = user.username,
                profilePictureUrl = user.profileImageUrl,
                bio = user.bio,
                isFollowing = isFollowing
            )
        }
    }

}