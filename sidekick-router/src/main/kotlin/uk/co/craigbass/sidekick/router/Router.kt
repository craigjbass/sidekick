package uk.co.craigbass.sidekick.router

import uk.co.craigbass.sidekick.router.Router.MessageType.UnRouted

class Router {
    fun route(channel: ExternalClientChannel) {
        channel.send(Message(UnRouted))
    }

    data class Message(var messageType: MessageType)

    enum class MessageType {
        UnRouted
    }

    interface ExternalClientChannel {
        fun send(message: Message)
    }
}
