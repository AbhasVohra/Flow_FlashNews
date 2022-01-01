package com.abhas.letsflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.abhas.letsflow.ui.theme.LetsFlowTheme
import com.abhas.letsflow.viewmodel.LatestNewsViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LetsFlowTheme {
                val newsViewModel = viewModel<LatestNewsViewModel>()
                val news = newsViewModel.latestNews.collectAsState("Happy New Year 2022")
                Surface(color = MaterialTheme.colors.surface) {
                    FlashNews(news)
                }
            }
        }
    }
}

@Composable
fun FlashNews(news: State<String>) {
    Text(
        text = news.value,
        fontSize = 24.sp,
        modifier = Modifier
            .fillMaxSize()
            .wrapContentWidth()
            .wrapContentHeight()
            .padding(8.dp),
        textAlign = TextAlign.Center
    )
}