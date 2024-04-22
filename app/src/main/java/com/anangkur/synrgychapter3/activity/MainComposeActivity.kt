package com.anangkur.synrgychapter3.activity

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainComposeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainComposeScreen()
        }
    }

    @Preview(showBackground = true)
    @Composable
    private fun TextOnly() {
        Text(
            modifier = Modifier.padding(16.dp),
            text = "test jetpack compose",
        )
    }

    @Preview(showBackground = true, showSystemUi = true)
    @Composable
    private fun MainComposeScreen() {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "Compose Activity") },
                )
            },
            content = { padding ->
                ContentList(padding = padding)
            },
        )
    }

    @Composable
    private fun ContentScreen(
        padding: PaddingValues,
        additionalText: String,
    ) {
        var state by remember { mutableStateOf(0) }
        var isShowButton3 by remember { mutableStateOf(true) }
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(text = "count: $state | $additionalText")
            Button(
                content = { Text(text = "Ini Button") },
                onClick = {
                    state++
                    isShowButton3 = state.mod(2) == 0
                },
            )
            Button(
                content = { Text(text = "Ini Button 2") },
                onClick = {},
            )
            if (isShowButton3) {
                Button(
                    content = { Text(text = "Ini Button 3") },
                    onClick = {},
                )
            }
        }
    }

    @Composable
    private fun ContentList(padding: PaddingValues) {
        var state by remember { mutableStateOf<List<String>>(arrayListOf()) }
        LazyColumn(modifier = Modifier.padding(padding)) {
            item { Text(text = "ini adalah text di dalam lazy column") }
            item {
                Button(
                    content = { Text(text = "ini adalah button") },
                    onClick = {
                        val tempState = state.toMutableList()
                        tempState.add("state ${state.size}")
                        state = tempState
                    },
                )
            }
            items(100) { number ->
                Text(text = "ini adalah item ke: $number")
            }
            items(listOf("item 1", "item 2")) { item ->
                Text(text = item)
            }
            items(state) { state ->
                Text(text = state)
            }
        }
    }
}