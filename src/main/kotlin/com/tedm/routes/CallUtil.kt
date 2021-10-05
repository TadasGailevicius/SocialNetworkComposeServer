package com.tedm.routes

import com.tedm.plugins.userId
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*
import io.ktor.util.pipeline.*

val ApplicationCall.userId: String
    get() = principal<JWTPrincipal>()?.userId.toString()