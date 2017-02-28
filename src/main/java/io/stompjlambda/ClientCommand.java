package io.stompjlambda;

public enum ClientCommand {
    SEND("SEND"),
    SUBSCRIBE("SUBSCRIBE"),
    BEGIN("BEGIN"),
    COMMIT("COMMIT"),
    ABORT("ABORT"),
    ACK("ACK"),
    NACK("NACK"),
    DISCONNECT("DISCONNECT"),
    CONNECT("CONNECT"),
    STOMP("STOMP");

    private final String command;

    private ClientCommand(final String text) {
        this.command = text;
    }

}
