package com.tedm.routes

import com.tedm.data.models.Post
import com.tedm.data.repository.post.PostRepository
import com.tedm.data.requests.CreateAccountRequest
import com.tedm.data.requests.CreatePostRequest
import com.tedm.data.responses.BasicApiResponse
import com.tedm.util.ApiResponseMessages
import com.tedm.util.ApiResponseMessages.USER_NOT_FOUND
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.createPostRoute(postRepository: PostRepository){
    post("/api/post/create"){
        val request = call.receiveOrNull<CreatePostRequest>() ?: kotlin.run {
            call.respond(HttpStatusCode.BadRequest)
            return@post
        }

        val didUserExist = postRepository.createPostIfUserExist(
            Post(
                imageUrl = "",
                userId = request.userId,
                timestamp = System.currentTimeMillis(),
                description = request.description,
            )
        )

        if(!didUserExist){
            call.respond(
                HttpStatusCode.OK,
                BasicApiResponse(
                    successful = false,
                    message = USER_NOT_FOUND
                )
            )
        } else {
            call.respond(
                HttpStatusCode.OK,
                BasicApiResponse(
                    successful = true
                )
            )
        }
    }
}