package com.dicoding.sahamiq.core.utils

import androidx.annotation.*
import java.util.concurrent.*

class AppExecutors @VisibleForTesting constructor(
    private val diskIO: Executor
) {

    constructor() : this(
        Executors.newSingleThreadExecutor()
    )

    fun diskIO(): Executor = diskIO

}
