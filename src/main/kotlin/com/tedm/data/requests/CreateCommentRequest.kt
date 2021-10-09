package com.tedm.data.requests

data class CreateCommentRequest(
    val comment: String,
    val postId: String,
)
