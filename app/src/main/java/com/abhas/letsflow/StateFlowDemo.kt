package com.abhas.letsflow

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.abhas.letsflow.ui.theme.LetsFlowTheme
import com.abhas.letsflow.viewmodel.LatestNewsViewModel

class StateFlowDemo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LetsFlowTheme {
                val newsViewModel = viewModel<LatestNewsViewModel>()
                val news = newsViewModel.stateFlow.collectAsState("")
                Surface(color = MaterialTheme.colors.surface) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Box(modifier = Modifier.size(20.dp))
                        Button(
                            onClick = { newsViewModel.randomNews() },
                            modifier = Modifier
                                .size(200.dp, 50.dp)
                                .align(CenterHorizontally),
                        ) {
                            Text(text = "Random News")
                        }
                        FlashNews(news)
                    }
                }
            }
        }
    }
}