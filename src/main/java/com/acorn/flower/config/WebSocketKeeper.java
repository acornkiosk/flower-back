package com.acorn.flower.config;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import com.acorn.flower.kiosk.KioskDao;
import com.acorn.flower.kiosk.KioskDto;
import com.acorn.flower.user.UserDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class WebSocketKeeper {

	/** 
	 * URL : 관리자 페이지와 키오스크 구분하는 용도 
	 * 사장님 => ws://localhost:9000/flower/ws/owner
	 * 키오스크 => ws://localhost:9000/flower/ws/kiosk/{id}
	 */
	@Data
	public class SessionDto{
		private String id;
		private URI uri;
		private String user;
		private String kioskId;
		private WebSocketSession session;
	}
	
	Map<String, SessionDto> map = new HashMap<String, SessionDto>();
	
	public void add(String id, URI uri, WebSocketSession session) {
		SessionDto dto = new SessionDto();
		dto.setId(id);
		dto.setUri(uri);
		dto.setUser(extractUserFromUri(uri));
		if(extractUserFromUri(uri).equals("kiosk")) {
			dto.setKioskId(KioskId(uri));
		}else {
			dto.setKioskId(null);
		}
		dto.setSession(session);
		
		map.put(id, dto);
		log.info("WebSocket session Add = {}", dto.toString());
	}
	public void delete(String id, URI uri) {
		String Requester = extractUserFromUri(uri);
		if(Requester.equals("owner")) {
			map.remove(id);
			log.info("WebSocket session Delete = 사장님 웹소켓 삭제");
		}else{
			map.remove(id);
			log.info("WebSocket session Delete = 키오스크 웹소켓 삭제");
		}
	}
	/** 키오스크 번호 주입 */
	public String KioskId(URI uri) {
		String kioskId = "";
		/** 사용자 구분하기 */
		String user = extractUserFromUri(uri);
		if(user.equals("kiosk")) {
			kioskId = extractIdFromUri(uri);
			log.info("WebSocket session Get = "+kioskId+"번 키오스크");
		}else {
			kioskId = "";
			log.warn("WebSocket session Get = 정보를 확인해보시기 바랍니다. 키오스크 정보가 아닙니다.");
		}
		return kioskId;
	}
	/** 사용자 식별단어 추출하기 */
	public static String extractUserFromUri(URI uri) {
		String path = uri.getPath();
		String[] segments = path.split("/");
		String requester = segments[3];
		return requester;
	}
	/** 키오스크 식별번호 추출하기 */
	public static String extractIdFromUri(URI uri) {
	    /** URI에서 경로를 가져옴 */
	    String path = uri.getPath();
	    /** '/' 기준으로 단어추츨 */
	    String[] segments = path.split("/");
	    /** 의도한 대상의 구조 : "/flower/ws/kiosk/{id}"
	     *  결과 : "", "flower", "ws", "kiosk", "{id}" => 5개 */
	    if (segments.length >= 4 && segments[3].equals("kiosk")) {
	        if (segments.length >= 5) {
	            /** "/flower/ws/kiosk/" 다음의 세그먼트가 ID */
	            return segments[4];
	        }
	    }
	    /** 올바른 형식이 아닌 경우 빈 문자열 반환 또는 예외 처리 */
	    log.warn("키오스크 번호를 확인할 수 없습니다.");
	    return null;
	}
	
	public List<WebSocketSession> getWs()  {
		List<WebSocketSession> list = new ArrayList<WebSocketSession>();
		Set<String> set = map.keySet();
		for(String key : set) {
			WebSocketSession session = map.get(key).getSession();
			list.add(session);
		}
		log.info("WebSocket session GetList = {}", list);
		return list;
	}
	
} // WebSocketKeeper 