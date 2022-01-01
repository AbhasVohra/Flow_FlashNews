package com.abhas.letsflow.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LatestNewsViewModel: ViewModel() {

    private val newsList = listOf<String>("World welcomes new year 2022 with high hopes, Happy New Year 2022 to all.", "WHO warns countries worldwide", "Elections on stake, who will win?", "The rise of IT raids", "End of example news")
    private val refreshInterval = 1000L

    val latestNews: Flow<String> = flow {
        for (s in newsList) {
            emit(s)
            delay(refreshInterval)
        }
    }
}