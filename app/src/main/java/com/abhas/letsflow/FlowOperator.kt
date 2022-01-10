package com.abhas.letsflow

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.abhas.letsflow.model.NewsCategory
import com.abhas.letsflow.model.NewsModel
import com.abhas.letsflow.ui.theme.LetsFlowTheme
import com.abhas.letsflow.viewmodel.LatestFilteredNewsViewModel

class FlowOperator : AppCompatActivity() {
    private var filteredNews = mutableListOf<NewsModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LetsFlowTheme {
                val filteredNewsViewModel = viewModel<LatestFilteredNewsViewModel>()
                val filteredNews: State<NewsModel> = filteredNewsViewModel.getCustomNewsFlow(NewsCategory.POLITICS).collectAsState(NewsModel(""))
                Surface(color = MaterialTheme.colors.surface) {
                    ListFilteredNews(news = filteredNews)
                }
            }
        }
    }

    @Composable
    fun ListFilteredNews(news: State<NewsModel>) {
        filteredNews.add(news.value)
        println("AppRouteList: $filteredNews")
        LazyColumn {
            items(filteredNews.size) { newsIndex ->
                NewsItem(news = filteredNews[newsIndex])
            }
        }
    }

    @Composable
    private fun NewsItem(news: NewsModel) {
        Text(
            text = news.headline,
            fontSize = 24.sp,
            modifier = Modifier
                .fillMaxSize()
                .wrapContentWidth()
                .wrapContentHeight()
                .padding(8.dp),
            textAlign = TextAlign.Center
        )
    }


}