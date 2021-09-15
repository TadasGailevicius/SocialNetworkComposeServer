package com.tedm.plugins

import com.tedm.routes.createUserRoute
import io.ktor.routing.*
import io.ktor.application.*

fun Application.configureRouting() {
    routing {
        createUserRoute()
    }
}
