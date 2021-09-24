package com.tedm.service

import com.tedm.data.models.Post
import com.tedm.data.repository.post.PostRepository
import com.tedm.data.requests.CreatePostRequest

class PostService(
    private val repository: PostRepository
) {
    suspend fun createPostIfUserExists(request: CreatePostRequest): Boolean {
        return repository.createPostIfUserExist(
            Post(
                imageUrl = "",
                userId = request.userId,
                timestamp = System.currentTimeMillis(),
                description = request.description,
            )
        )
    }
}