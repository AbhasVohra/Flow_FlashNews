package com.abhas.letsflow.viewmodel

import androidx.lifecycle.ViewModel
import com.abhas.letsflow.FlowBasic
import com.abhas.letsflow.FlowOperator
import com.abhas.letsflow.StateFlowDemo
import com.abhas.letsflow.model.AppRoute
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AppRouteListViewModel : ViewModel() {

    private val demoList = listOf<AppRoute>(
        AppRoute("Basic Flow", FlowBasic::class.java),
        AppRoute("Flow Operator", FlowOperator::class.java),
        AppRoute("StateFlow", StateFlowDemo::class.java),
    )

    var appRouteFlow: Flow<AppRoute> = flow {
        for (appRoute in demoList) {
            emit(appRoute)
            delay(300)
        }
    }
}