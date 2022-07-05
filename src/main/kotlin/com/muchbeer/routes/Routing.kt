package com.muchbeer.routes

import com.muchbeer.model.School
import com.muchbeer.repository.SchoolRepository
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

fun Application.configureRouting(repository : SchoolRepository) {

    // Starting point for a Ktor app:
    routing {
        get("/schools") {
            call.respond(repository.retrievAllSchool())
        }

        post("/register") {
            val addSchool = call.receive<School>()
            val response = repository.insertSchool(addSchool)
            call.respond(response)
        }
    }

    routing {
    }
}
