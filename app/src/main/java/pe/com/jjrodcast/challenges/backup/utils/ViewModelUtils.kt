package pe.com.jjrodcast.challenges.backup.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal suspend fun <T> io(block: suspend CoroutineScope.() -> T): T =
    withContext(Dispatchers.IO) {
        block(this)
    }