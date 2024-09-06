package server

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.flow
import quickfix.Session
import quickfix.field.*
import quickfix.fix44.MarketDataSnapshotFullRefresh
import java.util.concurrent.ConcurrentHashMap

class PriceGenerationManager {
    private val contextMap = ConcurrentHashMap<String, Job>()
    private val coroutineScope = CoroutineScope(Dispatchers.Default) // Uses Default dispatcher for background tasks

    private fun periodicFlow(periodMillis: Long) = flow {
        while (true) {
            emit(Unit) // Emit a unit value to signal that it's time to execute the task
            delay(periodMillis) // Wait for the specified period
        }
    }

    fun startPriceGeneration(name: String, session: Session?) {
        contextMap.computeIfAbsent(name) {
            coroutineScope.launch {
                periodicFlow(100L) // Emit every 100 milliseconds
                    .collect {
                        sendPrice(session) // Handle the task here
                    }
            }
        }
    }

    fun stopPriceGeneration(name: String?) {
        contextMap[name]?.let { job ->
            job.cancel() // Cancel the ongoing job
            contextMap.remove(name)
        }
    }

    private fun sendPrice(session: Session?) {
        val message = MarketDataSnapshotFullRefresh()
        message.header.setField(MsgType(MsgType.MARKET_DATA_SNAPSHOT_FULL_REFRESH))

        val symbol = Symbol("AAPL")
        val mdReqID = MDReqID("1")
        val entryType = MDEntryType(MDEntryType.BID)
        val mdEntryPx = MDEntryPx(150.25)
        val mdEntrySize = MDEntrySize(1000.0)

        message.setField(mdReqID)
        message.setField(symbol)

        val group = MarketDataSnapshotFullRefresh.NoMDEntries()
        group.setField(entryType)
        group.setField(mdEntryPx)
        group.setField(mdEntrySize)
        message.addGroup(group)

        session?.send(message)
        println("Sent price update for symbol AAPL")
    }
}