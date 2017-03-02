package io.stompjlambda.stream;

import io.stompjlambda.Frame;
import io.stompjlambda.StompClient;
import io.stompjlambda.StompException;
import io.stompjlambda.StompListener;

import java.io.IOException;

/**
 * StompStream takes care of the network protocol as defined in the spec:
 *
 * STOMP is a frame based protocol which assumes a reliable 2-way streaming
 * network protocol (such as TCP) underneath.
 * The client and server will communicate using STOMP frames sent over the stream.
 * (https://stomp.github.io/stomp-specification-1.2.html#STOMP_Frames)
 */
public interface StompStream {
    public void connect(String server, int port) throws StompException;

    public void send(Frame frame) throws StompException;

    Frame receive() throws StompException;

    public void setListener(StompListener listener);

    public void disconnect() throws StompException;

    boolean isConnected();

    default public Frame sendReceive(Frame frame) throws StompException {
        this.send(frame);
        return this.receive();
    }
}
