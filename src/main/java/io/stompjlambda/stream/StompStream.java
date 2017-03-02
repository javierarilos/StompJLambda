package io.stompjlambda.stream;

import io.stompjlambda.Frame;
import io.stompjlambda.StompClient;
import io.stompjlambda.StompException;
import io.stompjlambda.StompListener;

/**
 * STOMP is a frame based protocol which assumes a reliable 2-way streaming
 * network protocol (such as TCP) underneath.
 * The client and server will communicate using STOMP frames sent over the stream.
 * (https://stomp.github.io/stomp-specification-1.2.html#STOMP_Frames)
 */
public interface StompStream {
    public void connect(String server, int port, String host, String login, String passcode, int heartBeat) throws StompException;
    public void send(Frame frame) throws StompException;
    public void setListener(StompListener listener);
    public void disconnect() throws StompException;

    boolean isConnected();
}
