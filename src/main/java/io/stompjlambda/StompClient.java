package io.stompjlambda;

import io.stompjlambda.stream.StompStream;

public class StompClient implements StompListener {
    private String host = "127.0.0.1";
    private int port = 61613;
    private String usr = "guest";
    private String pass = "guest";
    private String transport = StompStreamFactory.TRANSPORT_TCP;

    private boolean connected = false;
    private StompStream stompStream;

    public void connect() throws StompException {
        stompStream = StompStreamFactory.getInstance(transport);
        stompStream.connect();
        connected = true;
    }

    public void frameReceived(Frame frame) {
        System.out.printf("Received frame! frame=%s %n", frame);

    }

    public StompClient(String host, int port, String usr, String pass) {
        this(host, port);
        this.usr = usr;
        this.pass = pass;
    }

    public StompClient(String host, int port) {
        this(host);
        this.port = port;
    }

    public StompClient(String host) {
        this.host = host;
    }

    public StompClient() {
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getUsr() {
        return usr;
    }

    public String getPass() {
        return pass;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

}
