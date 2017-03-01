package io.stompjlambda;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Frame class represents a frame according to the Stomp 1.2 protocol:
 * https://stomp.github.io/stomp-specification-1.2.html#STOMP_Frames
 */
class Frame {
    private static final String FRAME_TMPLT = "%s\n%s\n%s\0";
    private final Command command;
    private final Map<String, String> headers;
    private final String body;

    public static Frame newConnectFrame(String host, String login, String passcode, int heartBeat) {
        Map<String, String> headers = getMandatoryHeaders(host);
        headers.put("login", login);
        headers.put("passcode", passcode);
        headers.put("heart-beat", String.valueOf(heartBeat));
        return new Frame(Command.CONNECT, headers);
    }

    public String serialize() {
        String serializedHeaders = serializeHeaders();
        return String.format(FRAME_TMPLT, command, serializedHeaders, body);
    }

    private static Map<String,String> getMandatoryHeaders(String host) {
        Map<String, String> headers = new TreeMap<String, String>();
        headers.put("accept-version", "1.2");
        headers.put("host", host);
        return headers;
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

    public Frame(Command command, Map<String, String> headers, String body) {
        this.command = command;
        this.headers = headers;
        this.body = body;
    }

    public Frame(Command command, String body) {
        this(command, new HashMap<String, String>(), body);
    }

    public Frame(Command command) {
        this(command, "");
    }

    public Frame(Command command, Map<String, String> headers) {
        this(command, headers, "");
    }

    public Command getCommand() {
        return command;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }
}
