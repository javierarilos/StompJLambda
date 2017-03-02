package io.stompjlambda.stream;

import io.stompjlambda.StompClient;
import io.stompjlambda.StompException;

public class TryToStompSomething {
    public static void main(String[] args) throws StompException {

        StompClient client = new StompClient();
        client.connect();

        System.out.printf("CLIENT, IS CONNECTED %b %n", client.isConnected());
        client.send("/queue/niceq", "hello stomp");
        client.disconnect();
        System.out.printf("AFTER DISCONNECT - CLIENT, IS CONNECTED %b %n", client.isConnected());
    }
}
