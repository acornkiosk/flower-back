package com.acorn.flower.config;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.acorn.flower.kiosk.KioskDto;
import com.acorn.flower.order.OrderController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Controller
public class WebSocketHandler extends TextWebSocketHandler{
	
	@Autowired
	private WebSocketKeeper keeper;
	
	@Override 
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		super.afterConnectionEstablished(session);
		keeper.add(session.getId(), session.getUri(), session);
		System.out.println("ID 생성: " + session.getId()+" URL: "+session.getUri());
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		super.afterConnectionClosed(session, status);
		keeper.delete(session.getId(), session.getUri());
		System.out.println("ID 제거: "+session.getId()+" 코드: "+status.getCode()+ " 종료사유: "+status.getReason());
	}
	
	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		
		super.handleMessage(session, message);
		System.out.println("handleMessage: "+message.getPayload());
		
		List<WebSocketSession> list = keeper.getWs();
		for(WebSocketSession wss : list) {
			/** void 라서 반환값이 없는 게 아니다. senMessage 가 보내는 명령어다. */
			wss.sendMessage(message);
			System.out.println(wss);
		}
	}
	
}