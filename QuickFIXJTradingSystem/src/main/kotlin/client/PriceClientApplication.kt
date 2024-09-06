package client

import quickfix.*
import quickfix.Message
import quickfix.fix44.*
import quickfix.field.*

class PriceClientApplication : Application {
    override fun onCreate(sessionId: SessionID?) {
        println("Session created: $sessionId")
    }

    override fun onLogon(sessionId: SessionID?) {
        println("Logon: $sessionId")
    }

    override fun onLogout(sessionId: SessionID?) {
        println("Logout: $sessionId")
    }

    override fun toAdmin(message: Message?, sessionId: SessionID?) {}
    override fun fromAdmin(message: Message?, sessionId: SessionID?) {}
    override fun toApp(message: Message?, sessionId: SessionID?) {}

    override fun fromApp(message: Message, sessionId: SessionID?) {
        if (message is MarketDataSnapshotFullRefresh) {
            val symbol = message.getString(Symbol.FIELD)
            val price = message.getGroup(1, MarketDataSnapshotFullRefresh.NoMDEntries()).getDouble(MDEntryPx.FIELD)
            val size = message.getGroup(1, MarketDataSnapshotFullRefresh.NoMDEntries()).getInt(MDEntrySize.FIELD)
            println("Received price update: Symbol=$symbol, Price=$price, Size=$size")
        }
    }
}

fun main() {
    val settings = SessionSettings("client.cfg")
    val application = PriceClientApplication()
    val storeFactory = FileStoreFactory(settings)
    val logFactory = FileLogFactory(settings)
    val messageFactory = DefaultMessageFactory()

    val initiator = SocketInitiator(application, storeFactory, settings, logFactory, messageFactory)
    initiator.start()


    Thread.sleep(Long.MAX_VALUE)
}