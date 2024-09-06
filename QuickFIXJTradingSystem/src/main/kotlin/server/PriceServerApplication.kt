package server

import quickfix.*
import quickfix.Message

class PriceServerApplication(private val pricemanager: PriceGenerationManager) : Application {

    override fun onCreate(sessionId: SessionID?) {
        println("Session created: $sessionId")
    }

    override fun onLogon(sessionId: SessionID?) {
        println("Logon: $sessionId")
        val session = Session.lookupSession(sessionId)
        pricemanager.startPriceGeneration(session.sessionID.senderSubID, session);

    }

    override fun onLogout(sessionId: SessionID?) {
        println("Logout: $sessionId")
        pricemanager.stopPriceGeneration(sessionId?.senderSubID);
    }

    override fun toAdmin(message: Message?, sessionId: SessionID?) {}
    override fun fromAdmin(message: Message?, sessionId: SessionID?) {}
    override fun toApp(message: Message?, sessionId: SessionID?) {}
    override fun fromApp(message: Message?, sessionId: SessionID?) {}
}

fun main() {
    val settings = SessionSettings("server.cfg")
    val application = PriceServerApplication(PriceGenerationManager())
    val storeFactory = FileStoreFactory(settings)
    val logFactory = FileLogFactory(settings)
    val messageFactory = DefaultMessageFactory()

    val acceptor = SocketAcceptor(application, storeFactory, settings, logFactory, messageFactory)
    acceptor.start()
}