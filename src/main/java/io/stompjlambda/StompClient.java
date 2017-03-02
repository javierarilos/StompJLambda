package io.stompjlambda;

import io.stompjlambda.stream.StompStream;

public class StompClient implements StompListener {
    public static final String DEFAULT_HOST = "127.0.0.1";
    public static final int DEFAULT_PORT = 61613;
    public static final String DEFAULT_VHOST = "/";
    public static final String DEFAULT_USER = "guest";
    public static final String DEFAULT_PASSCODE = "guest";

    private final String host;
    private final int port;
    private final String vhost;
    private final String user;
    private final String passcode;
    private String transport = StompStreamFactory.TRANSPORT_TCP;

    private StompStream stompStream;

    public void connect() throws StompException {
        stompStream = StompStreamFactory.getInstance(transport);
        stompStream.connect(host, port, "/", user, passcode, 0);
    }

    public void disconnect() throws StompException {
        stompStream.disconnect();
    }

    public void frameReceived(Frame frame) {
        System.out.printf("Received frame! frame=%s %n", frame);
    }


    public StompClient(String host, int port, String vhost, String user, String passcode) {
        this.host = host;
        this.port = port;
        this.vhost = vhost;
        this.user = user;
        this.passcode = passcode;
    }

    public StompClient(String host, int port, String user, String passcode) {
        this(host, port, DEFAULT_VHOST, user, passcode);
    }

    public StompClient(String host, int port) {
        this(host, port, DEFAULT_VHOST, DEFAULT_USER, DEFAULT_PASSCODE);
    }

    public StompClient(String host) {
        this(host, DEFAULT_PORT, DEFAULT_VHOST, DEFAULT_USER, DEFAULT_PASSCODE);
    }

    public StompClient() {
        this(DEFAULT_HOST, DEFAULT_PORT, DEFAULT_VHOST, DEFAULT_USER, DEFAULT_PASSCODE);
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getUser() {
        return user;
    }

    public String getPasscode() {
        return passcode;
    }

    public boolean isConnected() {
        return stompStream.isConnected();
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    public void send(String destination, String body) throws StompException {
        Frame frame = Frame.newSendFrame(destination, body);
        stompStream.send(frame);
    }
}
