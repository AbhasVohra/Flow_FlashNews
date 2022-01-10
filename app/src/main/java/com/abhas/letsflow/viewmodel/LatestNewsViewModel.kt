package com.abhas.letsflow.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlin.random.Random

class LatestNewsViewModel: ViewModel() {

    private val newsList = listOf<String>("World welcomes new year 2022 with high hopes, Happy New Year 2022 to all.", "WHO warns countries worldwide", "Elections on stake, who will win?", "The rise of IT raids", "End of example news")
    private val refreshInterval = 1000L

    val latestNews: Flow<String> = flow {
        for (s in newsList) {
            emit(s)
            delay(refreshInterval)
        }
    }

    private val _stateFlow = MutableStateFlow("Click Random News, to get one.")
    var stateFlow = _stateFlow.asStateFlow()

    fun randomNews() {
        _stateFlow.value = newsList[Random.nextInt(0, 4)]
    }
}