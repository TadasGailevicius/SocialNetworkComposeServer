package com.tedm.service

import com.tedm.data.repository.likes.LikeRepository

class LikeService(
    private val repository: LikeRepository
) {

    suspend fun likeParent(userId: String, parentId: String, parentType: Int): Boolean {
        return repository.likeParent(
            userId = userId,
            parentId = parentId,
            parentType = parentType
        )
    }

    suspend fun unlikeParent(userId: String, parentId: String): Boolean {
        return repository.unlikeParent(
            userId = userId,
            parentId = parentId
        )
    }

    suspend fun deleteLikesForParent(parentId: String) {
        return repository.deleteLikesForParent(
            parentId = parentId
        )
    }

}