package server

import quickfix.*
import quickfix.Message
import quickfix.fix44.*
import quickfix.field.*
import java.util.*

class PriceServerApplication : Application {
    override fun onCreate(sessionId: SessionID?) {
        println("Session created: $sessionId")
    }

    override fun onLogon(sessionId: SessionID?) {
        println("Logon: $sessionId")
        val session = Session.lookupSession(sessionId)

        Timer().schedule(object : TimerTask() {
            override fun run() {
                sendPrice(session)
            }
        }, 0, 5000)  // Send price every 5 seconds
    }

    override fun onLogout(sessionId: SessionID?) {
        println("Logout: $sessionId")
    }

    override fun toAdmin(message: Message?, sessionId: SessionID?) {}
    override fun fromAdmin(message: Message?, sessionId: SessionID?) {}
    override fun toApp(message: Message?, sessionId: SessionID?) {}
    override fun fromApp(message: Message?, sessionId: SessionID?) {}

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

fun main() {
    val settings = SessionSettings("server.cfg")
    val application = PriceServerApplication()
    val storeFactory = FileStoreFactory(settings)
    val logFactory = FileLogFactory(settings)
    val messageFactory = DefaultMessageFactory()

    val acceptor = SocketAcceptor(application, storeFactory, settings, logFactory, messageFactory)
    acceptor.start()
}