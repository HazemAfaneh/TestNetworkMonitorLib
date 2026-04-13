package com.jetbrain.testnetworkmonitorlib.presentation

import com.jetbrain.testnetworkmonitorlib.data.remote.ApiService
import com.jetbrain.testnetworkmonitorlib.domain.model.Post
import com.jetbrain.testnetworkmonitorlib.domain.model.Todo
import com.jetbrain.testnetworkmonitorlib.domain.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

sealed class UiState {
    data object Idle : UiState()
    data object Loading : UiState()
    data class Success(val json: String) : UiState()
    data class Error(val message: String) : UiState()
}

class SampleViewModel(private val apiService: ApiService) {

    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    private val _uiState = MutableStateFlow<UiState>(UiState.Idle)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val prettyJson = Json {
        prettyPrint = true
        ignoreUnknownKeys = true
    }

    fun fetchPost() {
        scope.launch {
            _uiState.value = UiState.Loading
            try {
                val post = apiService.getPost()
                _uiState.value = UiState.Success(prettyJson.encodeToString<Post>(post))
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun fetchUser() {
        scope.launch {
            _uiState.value = UiState.Loading
            try {
                val user = apiService.getUser()
                _uiState.value = UiState.Success(prettyJson.encodeToString<User>(user))
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun fetchTodo() {
        scope.launch {
            _uiState.value = UiState.Loading
            try {
                val todo = apiService.getTodo()
                _uiState.value = UiState.Success(prettyJson.encodeToString<Todo>(todo))
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}
