package com.acorn.flower.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class WebSocketHandler extends TextWebSocketHandler{
	
	@Autowired
	private WebSocketKeeper keeper;
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		super.afterConnectionEstablished(session);
		keeper.add(session.getId(), session);
		System.out.println("session 생성됨" + session.getId());
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		super.afterConnectionClosed(session, status);
		keeper.delete(session.getId());
		System.out.println("session 종료됨"+session.getId());
	}
	
	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		super.handleMessage(session, message);
		System.out.println(message);
		List<WebSocketSession> list = keeper.getWs();
		for(WebSocketSession wss : list) {
			wss.sendMessage(message);
		}
	}
}