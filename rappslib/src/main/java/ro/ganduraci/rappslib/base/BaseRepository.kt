package ro.ganduraci.rappslib.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

open class BaseRepository {

    private val job = SupervisorJob()
    open val scope = CoroutineScope(Dispatchers.IO + job)

}