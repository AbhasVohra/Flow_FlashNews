package com.abhas.letsflow.viewmodel

import androidx.lifecycle.ViewModel
import com.abhas.letsflow.model.NewsCategory
import com.abhas.letsflow.model.NewsModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow

class LatestFilteredNewsViewModel : ViewModel() {

    private val newsList = listOf<NewsModel>(
        NewsModel("World welcomes new year 2022 with high hopes, Happy New Year 2022 to all.", listOf(NewsCategory.WORLD)),
        NewsModel("WHO warns countries worldwide", listOf(NewsCategory.WORLD, NewsCategory.PANDEMIC)),
        NewsModel("Elections on stake, who will win?", listOf(NewsCategory.INDIA, NewsCategory.POLITICS)),
        NewsModel("The rise of IT raids", listOf(NewsCategory.INDIA, NewsCategory.POLITICS)),
        NewsModel("End of example news")
    )
    private val refreshInterval = 1000L

    private val latestNews: Flow<NewsModel> = flow {
        for (s in newsList) {
            emit(s)
            delay(refreshInterval)
        }
    }

    fun getCustomNewsFlow(category: NewsCategory) : Flow<NewsModel> {
        return latestNews.filter { newsModel -> newsModel.category.contains(category) }
    }
}