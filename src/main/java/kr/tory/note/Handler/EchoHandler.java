package kr.tory.note.Handler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;


public class EchoHandler extends TextWebSocketHandler{
private static Logger logger = LoggerFactory.getLogger(EchoHandler.class);

    private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();
    private HashMap<String, Object> testdata = new HashMap<String, Object>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session)
            throws Exception {
    	Map<String, Object> map = session.getAttributes();
        sessionList.add(session);
        testdata.put(session.getId(),map.get("val"));
        logger.info("{} 연결됨", session.getId());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session,
        TextMessage message) throws Exception {
        logger.info("{}로 부터 {} 받음", session.getId(), message.getPayload());
        for(WebSocketSession sess : sessionList){
            sess.sendMessage(new TextMessage(testdata.get(session.getId()) +" : "+ message.getPayload()));
        }
    }
    @Override
    public void afterConnectionClosed(WebSocketSession session,
            CloseStatus status) throws Exception {
        sessionList.remove(session);
        logger.info("{} 연결 끊김", session.getId());
    }
}
