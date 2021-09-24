package com.tedm.routes

import com.tedm.data.requests.CreatePostRequest
import com.tedm.data.responses.BasicApiResponse
import com.tedm.service.PostService
import com.tedm.service.UserService
import com.tedm.util.ApiResponseMessages.USER_NOT_FOUND
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.createPostRoute(
    postService: PostService,
    userService: UserService
) {
    authenticate {
        post("/api/post/create") {
            val request = call.receiveOrNull<CreatePostRequest>() ?: kotlin.run {
                call.respond(HttpStatusCode.BadRequest)
                return@post
            }

            val email = call.principal<JWTPrincipal>()?.getClaim("email", String::class)
            val isEmailByUser = userService.doesEmailBelongToUserId(
                email = email ?: "",
                userId = request.userId
            )
            if (!isEmailByUser) {
                call.respond(
                    status = HttpStatusCode.Unauthorized,
                    message = "Invalid user detected"
                )
                return@post
            }

            val didUserExist = postService.createPostIfUserExists(request)

            if (!didUserExist) {
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

}