package io.stompjlambda;

import junit.framework.TestCase;

import java.util.TreeMap;
import java.util.Map;

public class TestFrame extends TestCase {
    public void testFrameConstructor() {
        Frame frame = createConnectFrame();

        assertEquals(ClientCommand.CONNECT, frame.getCommand());
        assertTrue(frame.getHeaders().containsKey("header1"));
        assertTrue(frame.getHeaders().containsKey("header2"));
        assertEquals("value1", frame.getHeaders().get("header1"));
        assertEquals("value2", frame.getHeaders().get("header2"));
        assertEquals("some stomp message body contents", frame.getBody());
    }

    public void testConnectFrameSerialization() {
        Frame connectFrame = createConnectFrame();

        String expected = "CONNECT\nheader1:value1\nheader2:value2\n\nsome stomp message body contents\0";
        assertEquals(expected, connectFrame.serialize());
    }

    private Frame createConnectFrame() {
        Map<String, String> headers = new TreeMap<String, String>();
        headers.put("header1", "value1");
        headers.put("header2", "value2");
        String body = "some stomp message body contents";
        return new Frame(ClientCommand.CONNECT, headers, body);
    }


}
