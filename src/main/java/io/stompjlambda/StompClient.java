package io.stompjlambda;

public class StompClient {
    private String host = "127.0.0.1";
    private int port = 61613;
    private String usr = "guest";
    private String pass = "guest";

    private boolean connected = false;

    public void connect() {
        this.connected = true;
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

}
