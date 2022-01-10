package com.abhas.letsflow

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.abhas.letsflow.model.AppRoute
import com.abhas.letsflow.ui.theme.LetsFlowTheme
import com.abhas.letsflow.viewmodel.AppRouteListViewModel

class MainActivity : ComponentActivity() {

    var appRouteList = mutableListOf<AppRoute>()

    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LetsFlowTheme {
                val appRouteListViewModel = viewModel<AppRouteListViewModel>()
                val appRouteState: State<AppRoute> =
                    appRouteListViewModel.appRouteFlow.collectAsState(
                        AppRoute("", FlowBasic::class.java)
                    )
                Surface(color = MaterialTheme.colors.surface) {
                    ListAppRoute(appRouteState)
                }
            }
        }
    }

    @ExperimentalAnimationApi
    @Composable
    fun ListAppRoute(appRouteState: State<AppRoute>) {
        appRouteList.add(appRouteState.value)
        println("AppRouteList: $appRouteList")
        LazyColumn {
            items(appRouteList.size) { appRoute ->
                ListItemComposable(appRouteList[appRoute])
            }
        }
    }

    @ExperimentalAnimationApi
    @Composable
    private fun ListItemComposable(appRoute: AppRoute) {
        val intent = Intent(MainActivity@ this, appRoute.activity)
        val state = remember {
            MutableTransitionState(false).apply {
                // Start the animation immediately.
                targetState = true
            }
        }
        AnimatedVisibility(
            visibleState = state,
            enter = expandHorizontally(
                // Expand from the top.
                expandFrom = if (appRouteList.size % 3 == 0) Alignment.Start else if (appRouteList.size % 2 == 0) Alignment.End else Alignment.CenterHorizontally
            ) + fadeIn(
                // Fade in with the initial alpha of 0.3f.
                initialAlpha = 0.3f
            ),
            exit = slideOutVertically() + shrinkVertically() + fadeOut()
        ) {
            Button(
                onClick = {
                    startActivity(intent)
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.LightGray,
                    contentColor = Color.Red
                )
            ) {
                Text(
                    text = appRoute.title,
                    fontSize = 24.sp,
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentWidth()
                        .wrapContentHeight()
                        .padding(8.dp),
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}