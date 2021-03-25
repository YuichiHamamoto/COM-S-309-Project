package com.spring.backend.app;

import java.io.EOFException;
import java.io.IOException;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author Yuichi Hamamoto
 *
 */
@ServerEndpoint(value = "/version")
@Component
public class ExampleSocket {

    private final Logger logger = LoggerFactory.getLogger(ExampleSocket.class);

    /**
     * This method is called when the connection between the server and client is
     * established. The "session" object is the representation of the client. We are
     * going to be sending a string saying "Connected" to inform the client that the
     * connection was established successfully.
     *
     * @param session
     */
    @OnOpen
    public void onOpen(Session session) {
        try {
            logger.info("Client connected");
            session.getBasicRemote().sendText("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is called when the server receives a request from the client. Use
     * this method to send the version of the app to client
     * messages.
     * @param session  the representation of the client
     * @param messsage Message sent by the client
     */
    @OnMessage
    public void onMessage(Session session, String messsage) {
        //Here send a hard coded value
        logger.info("Message Received: " + messsage);
        sendMessage(session, "version: 1.0.2");
    }

    /**
     * This method is called when the connection between the client and server is
     * closed. It is also called after onError if client is disconnected due to the
     * error. You should use this method to clear any data and update any values you
     * need to before the client disconnects.
     *
     * ** Be careful about trying to send things to the client here as the client
     * has probably already disconnected and is not receiving anything. **
     *
     * @param session the representation of the client
     */
    @OnClose
    public void onClose(Session session) {
        logger.info("Client disconnected");
    }

    /**
     * This method is called when an error has occurred between the client and the
     * server. If the error is fatal, the connection will also close.
     *
     * @param session   the representation of the client
     * @param throwable The exception that was thrown
     */

    @OnError
    public void onError(Session session, Throwable throwable) {
        if (!throwable.getClass().getSimpleName().equals("EOFException")) {
            logger.debug("An error has occurred");
            throwable.printStackTrace();
        }

    }

    /**
     * Helper method to send a string to the given client
     *
     * @param session The representation of the client
     * @param message The message to be sent
     */
    private void sendMessage(Session session, String message) {
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

