package com.acorn.flower.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration // 설정클래스로 지정 
@EnableWebSocket // 클래스를 웹소켓으로 만듬 
public class WebSocketConfig implements WebSocketConfigurer {
	
	
	@Autowired
	private WebSocketHandler webSocketHandler;

	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {

		registry
			/** 웹소켓 신호를 모두 webSocketHandler 로 처리하도록 선언 */
			.addHandler(webSocketHandler
						, "/ws/kiosk/{id}" // flower_kiosk => WebSocket.js
						, "/ws/owner" // flower_front => webSocket.js
						)
			/** 요청지 url 은 밑에 두가지 링크에서만 받도록 제한을 둠 */
			.setAllowedOrigins("http://localhost:3000", "http://localhost:3030");
	}
}