package io.stompjlambda;

public enum Command {

    // ----- CLIENT COMMANDS
    SEND("SEND"),
    SUBSCRIBE("SUBSCRIBE"),
    BEGIN("BEGIN"),
    COMMIT("COMMIT"),
    ABORT("ABORT"),
    ACK("ACK"),
    NACK("NACK"),
    DISCONNECT("DISCONNECT"),
    CONNECT("CONNECT"),
    STOMP("STOMP"),

    // ----- SERVER COMMANDS
    CONNECTED("CONNECTED"),
    MESSAGE("MESSAGE"),
    RECEIPT("RECEIPT"),
    ERROR("ERROR");

    private final String command;

    private Command(final String text) {
        this.command = text;
    }

}
