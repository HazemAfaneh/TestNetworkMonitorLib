package com.jetbrain.testnetworkmonitorlib.data.remote

import com.jetbrain.testnetworkmonitorlib.domain.model.Post
import com.jetbrain.testnetworkmonitorlib.domain.model.Todo
import com.jetbrain.testnetworkmonitorlib.domain.model.User
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

private const val BASE_URL = "https://jsonplaceholder.typicode.com"

class ApiService(private val httpClient: HttpClient) {

    suspend fun getPost(): Post =
        httpClient.get("$BASE_URL/posts/1").body()

    suspend fun getUser(): User =
        httpClient.get("$BASE_URL/users/1").body()

    suspend fun getTodo(): Todo =
        httpClient.get("$BASE_URL/todos/1").body()
}
