package jp.speakbuddy.edisonandroidexercise.network.http

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.engine.cio.endpoint
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class EdisonHttpClientBuilder {

    private var protocol: URLProtocol = URLProtocol.HTTPS
    private lateinit var host: String
    //private var port: Int? = null

    fun protocol(protocol: URLProtocol) = apply { this.protocol = protocol }

    fun host(host: String) = apply { this.host = host }

    //fun port(port: Int) = apply { this.port = port }

    fun build(): HttpClient {
        return HttpClient(CIO) {
            expectSuccess = true

            engine {
                endpoint {
                    keepAliveTime = 5000
                    connectTimeout = 5000
                    connectAttempts = 3
                }
            }

            defaultRequest {
                url {
                    protocol = this@EdisonHttpClientBuilder.protocol
                    host = this@EdisonHttpClientBuilder.host
                    //this@EdisonHttpClientBuilder.port?.let { port = it }
                }
                header(HttpHeaders.ContentType, "application/json")
            }

            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                    },
                )
            }

            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        println(message)
                    }
                }
                level = LogLevel.ALL
            }
        }
    }
}
