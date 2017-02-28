package io.stompjlambda;

import java.util.HashMap;
import java.util.Map;

/**
 * Frame class represents a frame according to the Stomp 1.2 protocol:
 * https://stomp.github.io/stomp-specification-1.2.html#STOMP_Frames
 */
class Frame {
    private static final String FRAME_TMPLT = "%s\n%s\n%s\0";
    private final ClientCommand command;
    private final Map<String, String> headers;
    private final String body;

    public String serialize() {
        String serializedHeaders = serializeHeaders();
        return String.format(FRAME_TMPLT, command, serializedHeaders, body);
    }

    private String serializeHeaders() {
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<String, String> entry : headers.entrySet()){
            String headerName = entry.getKey();
            String value = entry.getValue();
            sb.append(headerName).append(":").append(value).append("\n");
        }
        return sb.toString();
    }

    public Frame(ClientCommand command, Map<String, String> headers, String body) {
        this.command = command;
        this.headers = headers;
        this.body = body;
    }

    public Frame(ClientCommand command, String body) {
        this(command, new HashMap<String, String>(), body);
    }

    public Frame(ClientCommand command) {
        this(command, "");
    }

    public Frame(ClientCommand command, Map<String, String> headers) {
        this(command, headers, "");
    }

    public ClientCommand getCommand() {
        return command;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }
}
