package com.abhas.letsflow.model

import androidx.appcompat.app.AppCompatActivity

data class AppRoute(val title: String, val activity: Class<out AppCompatActivity>)
