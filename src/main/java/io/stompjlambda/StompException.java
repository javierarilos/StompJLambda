package io.stompjlambda;

import java.io.IOException;

public class StompException extends Exception {
    public StompException(String msg, IOException e) {
        super(msg, e);
    }

    public StompException(String msg) {
        super(msg);
    }
}
