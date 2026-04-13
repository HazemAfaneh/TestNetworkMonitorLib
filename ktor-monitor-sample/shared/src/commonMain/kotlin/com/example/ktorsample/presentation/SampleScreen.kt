package com.example.ktorsample.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import org.koin.compose.koinInject

@Composable
fun SampleScreen(viewModel: SampleViewModel = koinInject()) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Ktor Monitor Test",
            style = MaterialTheme.typography.headlineMedium,
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { viewModel.fetchPost() },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Fetch Post")
        }

        Button(
            onClick = { viewModel.fetchUser() },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Fetch User")
        }

        Button(
            onClick = { viewModel.fetchTodo() },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Fetch Todo")
        }

        Spacer(modifier = Modifier.height(16.dp))

        ResultCard(uiState = uiState)
    }
}

@Composable
private fun ResultCard(uiState: UiState) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
        ) {
            when (uiState) {
                is UiState.Idle -> Text(
                    text = "Press a button to make a request.",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )

                is UiState.Loading -> CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                )

                is UiState.Success -> Text(
                    text = uiState.json,
                    fontFamily = FontFamily.Monospace,
                    style = MaterialTheme.typography.bodySmall,
                )

                is UiState.Error -> Text(
                    text = "Error: ${uiState.message}",
                    color = MaterialTheme.colorScheme.error,
                )
            }
        }
    }
}
