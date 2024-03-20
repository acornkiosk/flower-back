package com.acorn.flower.config;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import com.acorn.flower.kiosk.KioskDao;
import com.acorn.flower.kiosk.KioskDto;
import com.acorn.flower.user.UserDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
		private URI uri; // 요청자 구분하기 
		private WebSocketSession session;
	}
	
	Map<String, SessionDto> map = new HashMap<String, SessionDto>();
	
	public void add(String id, URI uri, WebSocketSession session) {
		SessionDto dto = new SessionDto();
		dto.setId(id);
		dto.setUri(uri);
		dto.setSession(session);
		
		map.put(id, dto);
		System.out.println("웹소켓 session 추가");
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
	    return "대상이 아닙니다.";
	}
	/** TODO : 하나만 삭제할 때 다른 session 에도 주는 지 확인차 구현중 */
	public void delete(String id, URI uri) {
		String Requester = extractUserFromUri(uri);
		if(Requester.equals("owner")) {
			map.remove(id);
			System.out.println("웹소켓 사장님 삭제");
		}else {
			map.remove(id);
			System.out.println("웹소켓 kiosk 삭제");
		}
	}
	
	public WebSocketSession find(String id) {
		SessionDto dto = map.get(id);
		System.out.println("웹소켓 session 가져옴");
		return dto.getSession();
	}
	
	public List<WebSocketSession> getWs()  {
		List<WebSocketSession> list = new ArrayList<WebSocketSession>();
		System.out.println("웹소켓 session list를 가져옴");
		
		Set<String> set = map.keySet();
		for(String key : set) {
			WebSocketSession session = map.get(key).getSession();
			list.add(session);
		}
		return list;
	}
	
} // WebSocketKeeper 