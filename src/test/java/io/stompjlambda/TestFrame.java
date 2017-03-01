package io.stompjlambda;

import junit.framework.TestCase;

import java.util.TreeMap;
import java.util.Map;

public class TestFrame extends TestCase {
    public void testFrameConstructor() {
        Frame frame = createSendFrame();

        assertEquals(ClientCommand.SEND, frame.getCommand());
        assertTrue(frame.getHeaders().containsKey("header1"));
        assertTrue(frame.getHeaders().containsKey("header2"));
        assertEquals("value1", frame.getHeaders().get("header1"));
        assertEquals("value2", frame.getHeaders().get("header2"));
        assertEquals("some stomp message body contents", frame.getBody());
    }

    public void testConnectFrameSerialization() {
        Frame connectFrame = Frame.newConnectFrame("/", "guest", "guest", 15);

        String expected = "CONNECT\naccept-version:1.2\nheart-beat:15\nhost:/\nlogin:guest\npasscode:guest\n\n\0";
        assertEquals(expected, connectFrame.serialize());
    }

    private Frame createSendFrame() {
        Map<String, String> headers = new TreeMap<String, String>();
        headers.put("header1", "value1");
        headers.put("header2", "value2");
        String body = "some stomp message body contents";
        return new Frame(ClientCommand.SEND, headers, body);
    }


}
