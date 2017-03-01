package io.stompjlambda;

import io.stompjlambda.stream.FakeStompStream;
import io.stompjlambda.stream.StompStream;

import java.security.InvalidParameterException;

public class StompStreamFactory {
    public static final String TRANSPORT_TCP = "TCP";
    public static final String TRANSPORT_FAKE = "FAKE";
    public static final String TRANSPORT_DEFAULT = TRANSPORT_TCP;

    public static StompStream getInstance() {
        return getInstance(TRANSPORT_DEFAULT);
    }

    public static StompStream getInstance(String transport) {
        switch (transport) {
            case TRANSPORT_TCP:
                throw new RuntimeException("TRANSPORT_TCP NOT YET IMPLEMENTED.");
            case TRANSPORT_FAKE:
                return new FakeStompStream();
            default:
                throw new InvalidParameterException(String.format("Transport: %s is NOT supported.", transport));
        }

    }
}
