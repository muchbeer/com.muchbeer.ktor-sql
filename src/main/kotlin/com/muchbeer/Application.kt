package com.muchbeer

import com.muchbeer.db.DatabaseFactory
import com.muchbeer.repository.SchoolRepository
import com.muchbeer.repository.SchoolRepositoryImpl
import com.muchbeer.routes.configureRouting
import io.ktor.server.application.*
import io.ktor.serialization.jackson.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import kotlin.reflect.jvm.internal.impl.resolve.NonReportingOverrideStrategy

fun main(args: Array<String>) {


    embeddedServer(Netty, port = 2016, host = "127.0.0.2") {
        DatabaseFactory()

        val repository : SchoolRepository = SchoolRepositoryImpl()
        install(ContentNegotiation) {
            jackson()
        }

        configureRouting(repository)
    }.start(wait = true)
}

