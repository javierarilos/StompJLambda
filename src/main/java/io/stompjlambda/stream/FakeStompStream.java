package io.stompjlambda.stream;


import io.stompjlambda.Frame;
import io.stompjlambda.StompClient;
import io.stompjlambda.StompException;
import io.stompjlambda.StompListener;

public class FakeStompStream implements StompStream {

    @Override
    public void connect(String server, int port) throws StompException {
        return;
    }

    @Override
    public void send(Frame frame) {
        return;
    }

    @Override
    public Frame receive() throws StompException {
        return null;
    }

    @Override
    public void setListener(StompListener listener) {
        return;
    }

    @Override
    public void disconnect() throws StompException {
        return;
    }

    @Override
    public boolean isConnected() {
        return true;
    }
}
