package com.tedm.service

import com.tedm.data.models.Post
import com.tedm.data.repository.post.PostRepository
import com.tedm.data.requests.CreatePostRequest
import com.tedm.util.Constants

class PostService(
    private val repository: PostRepository
) {
    suspend fun createPost(request: CreatePostRequest, userId: String, imageUrl: String): Boolean {
        return repository.createPost(
            Post(
                imageUrl = imageUrl,
                userId = userId,
                timestamp = System.currentTimeMillis(),
                description = request.description,
            )
        )
    }

    suspend fun getPostsForFollows(
        userId: String,
        page: Int = 0,
        pageSize: Int = Constants.DEFAULT_POST_PAGE_SIZE
    ): List<Post> {
        return repository.getPostsByFollows(
            userId = userId,
            page = page,
            pageSize = pageSize
        )
    }

    suspend fun getPostsForProfile(
        userId: String,
        page: Int = 0,
        pageSize: Int = Constants.DEFAULT_POST_PAGE_SIZE
    ): List<Post> {
        return repository.getPostsForProfile(
            userId = userId,
            page = page,
            pageSize = pageSize
        )
    }

    suspend fun getPost(
        postId: String
    ): Post? {
        return repository.getPost(
            postId = postId
        )
    }

    suspend fun deletePost(
        postId: String
    ) {
        return repository.deletePost(
            postId = postId
        )
    }
}