package com.tedm.data.repository.post

import com.tedm.data.models.Post
import com.tedm.util.Constants

interface PostRepository {

    suspend fun createPost(post: Post) : Boolean

    suspend fun deletePost(postId: String)

    suspend fun getPostsByFollows(
        userId: String,
        page: Int = 0,
        pageSize: Int = Constants.DEFAULT_POST_PAGE_SIZE,
    ): List<Post>

    suspend fun getPostsForProfile(
        userId: String,
        page: Int = 0,
        pageSize: Int = Constants.DEFAULT_POST_PAGE_SIZE,
    ): List<Post>

    suspend fun getPost(postId: String): Post?
}