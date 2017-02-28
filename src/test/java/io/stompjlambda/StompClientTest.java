package io.stompjlambda;

import junit.framework.TestCase;

public class StompClientTest extends TestCase{
    public void testStompClientConstructor() {
        StompClient client = new StompClient("somehost", 9999, "someuser", "somepass");

        assertEquals("somehost", client.getHost());
        assertEquals(9999, client.getPort());
        assertEquals("someuser", client.getUsr());
        assertEquals("somepass", client.getPass());
    }
}
