package io.stompjlambda;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;

/**
 * Frame class represents a frame according to the Stomp 1.2 protocol:
 * https://stomp.github.io/stomp-specification-1.2.html#STOMP_Frames
 */
public class Frame {
    private static final String FRAME_TMPLT = "%s\n%s\n%s\0";
    public static final String STOMP_VERSION = "1.2";

    private Command command;
    private Map<String, String> headers;
    private String body;

    public static Frame newConnectFrame(String host, String login, String passcode, int heartBeat) {
        Map<String, String> headers = new TreeMap<String, String>();
        headers.put("accept-version", STOMP_VERSION);
        headers.put("host", host);
        headers.put("login", login);
        headers.put("passcode", passcode);
        headers.put("heart-beat", "0," + String.valueOf(heartBeat));
        return new Frame(Command.CONNECT, headers);
    }

    public static Frame newDisconnectFrame(String receipt) {
        return new Frame(Command.DISCONNECT)
                .addHeader("receipt", receipt);
    }

    public static Frame newSendFrame(String destination, String body) {
        return new Frame(Command.SEND, body)
                .addHeader("destination", destination)
                .addHeader("content-type", "text/plain"); //for the moment only text msgs are supported
    }

    public static Frame deserialize(String frameStr) {
        String[] frameParts = frameStr.split("\n");

        Frame frame = new Frame();
        frame.command = Command.valueOf(frameParts[0]);
        boolean pendingHeaders = true;
        int currPosition = 1;
        while (pendingHeaders){
            String currPart = frameParts[currPosition];
            if (currPart.isEmpty()) { // no more headers
                pendingHeaders = false;
            } else {
                String[] headerParts = currPart.split(":");
                String header = headerParts[0];
                String headerValue = headerParts[1];
                frame.addHeader(header, headerValue);
            }
            currPosition++;
        }
        frame.body = frameParts[currPosition].replace("\0", "");
        return frame;
    }

    public String serialize() {
        String serializedHeaders = serializeHeaders();
        return String.format(FRAME_TMPLT, command, serializedHeaders, body);
    }

    public Frame addHeader(String header, String value) {
        this.headers.put(header, value);
        return this;
    }

    private String serializeHeaders() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : headers.entrySet()) {
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

    private Frame() {
        this.headers = new HashMap<String, String>();
    }

    public Frame(Command command, Map<String, String> headers) {
        this(command, headers, "");
    }

    @Override
    public String toString() {
        return serialize();
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

    public String getHeader(String header) {
        return this.headers.getOrDefault(header, "");
    }
}
