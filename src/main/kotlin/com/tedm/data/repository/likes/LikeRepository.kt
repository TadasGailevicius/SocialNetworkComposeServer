package com.tedm.data.repository.likes

import com.tedm.data.models.Like
import com.tedm.util.Constants

interface LikeRepository {

    suspend fun likeParent(userId: String, parentId: String, parentType: Int): Boolean

    suspend fun unlikeParent(userId: String, parentId: String): Boolean

    suspend fun deleteLikesForParent(parentId: String)

    suspend fun getLikesForParent(parentId: String, page: Int = 0, pageSize: Int = Constants.DEFAULT_ACTIVITY_PAGE_SIZE): List<Like>
}