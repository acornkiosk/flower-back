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

@Slf4j
@Controller
public class WebSocketHandler extends TextWebSocketHandler{
	
	@Autowired
	private WebSocketKeeper keeper;
	
	@Override 
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		super.afterConnectionEstablished(session);
		keeper.add(session.getId(), session.getUri(), session);
		log.info("WebSocket Connected = {}", "ID: "+session.getId()+" URL: "+session.getUri());
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		super.afterConnectionClosed(session, status);
		keeper.delete(session.getId(), session.getUri());
		/** 에러 사유를 보여주는 코드 */
		String reason = getCloseEventCodeReason(status.getCode(), status.getReason());
		log.info("WebSocket Unconnected = {}", "ID: "+session.getId()+" 코드: "+status.getCode()+ " 종료사유: "+reason);
	}
	
	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		
		super.handleMessage(session, message);
		log.info("WebSocket HandleMessage: "+message.getPayload());
		
		List<WebSocketSession> list = keeper.getWs();
		for(WebSocketSession wss : list) {
			/** void 라서 반환값이 없는 게 아니다. senMessage 가 보내는 명령어다. */
			wss.sendMessage(message);
			log.info("WebSocket 메시지 전달 경로 "+wss);
		}
	}
	
	/** 웹소켓 에러코드 */
	public static String getCloseEventCodeReason(int event, String defaultReason) {
        switch (event) {
            case 1000: return "로그아웃 하셨습니다.";
            case 1001: return "서버 혹은 웹브라우저를 이탈 하셨습니다.";
            case 1002: return "프로토콜 오류";
            case 1003: return "허용되지 않은 데이터 유형을 수신하여 종료합니다.";
            case 1005: return "상태 코드가 없는 채로 종료되었습니다.";
            case 1006: return "close 명령없이 비정상적으로 종료되었습니다.";
            case 1007: return "서버에 설정한 엔드포인트(config) 내에서 일치하지 않은 데이터를 받았습니다.";
            case 1008: return "정책위반 짜샤";
            case 1009: return "메시지 처리 용량 초과되었습니다.";
            case 1010: return "웹소켓 핸드세이크의 응답 메시지에서 데이터를 반환하지 못했습니다.";
            case 1011: return "서버가 요청을 완료하는데 방해가 되는 예기치 않은 상황에 직면하여 연결을 종료합니다.";
            case 1015: return "TLS 핸드세이크 수행 실패(인증서 검증불가)";
            default: return defaultReason != null ? defaultReason : "Unknown code: " + event;
        }
    }
	
}