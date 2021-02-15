package com.example

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*
import io.ktor.websocket.*
import io.ktor.http.cio.websocket.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(WebSockets)
    routing {
        webSocket("/echo"){
            send(Frame.Text("Bienvenido al echo server"))
            while(true) {
                val frame = incoming.receive()
                if(frame is Frame.Text)
                    send(Frame.Text("El cliente dijo: " + frame.readText()))

            }

        }
    }

}

