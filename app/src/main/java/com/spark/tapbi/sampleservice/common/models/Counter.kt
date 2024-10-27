package com.spark.tapbi.sampleservice.common.models

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class Counter {
    private var counterValue: Long = 0
    private var isRunning: Boolean = true

    fun start(): Flow<Long> = flow {
        while (isRunning) {
            emit(counterValue)
            delay(1000)
            counterValue++
        }
    }

    fun stop() {
        isRunning = false
        counterValue = 0
    }
}