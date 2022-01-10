package com.abhas.letsflow.model

data class NewsModel(val headline: String, val category: List<NewsCategory> = listOf(NewsCategory.GENERIC), val description: String = "$headline Description")
