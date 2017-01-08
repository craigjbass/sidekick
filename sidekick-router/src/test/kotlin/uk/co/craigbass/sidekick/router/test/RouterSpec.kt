package uk.co.craigbass.sidekick.router.test

import com.winterbe.expekt.should
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import uk.co.craigbass.sidekick.router.Router
import uk.co.craigbass.sidekick.router.Router.ExternalClientChannel
import uk.co.craigbass.sidekick.router.Router.MessageType.UnRouted

class RouterSpec : Spek({
    describe("Given there is nothing listening to route") {
        it("Sends an un-routed message to the external client channel") {
            val channel = VerifyingExternalClientChannel()
            Router().route(channel)
            channel.shouldHaveReceivedMessageWithType(UnRouted)
        }
    }
})

class VerifyingExternalClientChannel: ExternalClientChannel {
    var receivedMessage: Router.Message? = null

    override fun send(message: Router.Message) {
        receivedMessage = message
    }

    fun shouldHaveReceivedMessageWithType(messageType: Router.MessageType) {
        receivedMessage.should.not.be.`null`
        receivedMessage?.messageType.should.be.equal(messageType)
    }
}

