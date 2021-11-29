package com.haslett.pokemon.data.remote

import com.haslett.pokemon.data.remote.dto.PostRequest
import com.haslett.pokemon.data.remote.dto.PostResponse
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlin.text.get

class PostServiceImpl(
    private val client: HttpClient
): PostsService  {
    
    override suspend fun getPosts(): List<PostResponse> {
        // Add in parameters
        // parameter()
        return try {
            client.get{ url(HttpRoutes.POSTS) }
        } catch (e: RedirectResponseException) {
            // 3xx - responses
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: ClientRequestException) {
            // 4xx - responses
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: ServerResponseException) {
            // 5xx - responses
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: Exception) {
            // General catch all exception
            println("Error: ${e.message}")
            emptyList()
        }
    }
    
    override suspend fun createPost(postRequest: PostRequest): PostResponse? {
        return try {
            client.post<PostResponse>{
                url(HttpRoutes.POSTS)
                contentType(ContentType.Application.Json)
                body = postRequest
            }
        } catch (e: RedirectResponseException) {
            // 3xx - responses
            println("Error: ${e.response.status.description}")
            null
        } catch (e: ClientRequestException) {
            // 4xx - responses
            println("Error: ${e.response.status.description}")
            null
        } catch (e: ServerResponseException) {
            // 5xx - responses
            println("Error: ${e.response.status.description}")
            null
        } catch (e: Exception) {
            // General catch all exception
            println("Error: ${e.message}")
            null
        }
    }
    
}
