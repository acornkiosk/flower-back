package com.acorn.flower.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

@Component
public class WebSocketKeeper {
	Map<String, WebSocketSession> map = new HashMap<String, WebSocketSession>();
	
	public void add(String id, WebSocketSession session) {
		map.put(id, session);
		System.out.println("추가됨");
	}
	
	public void delete(String id) {
		map.remove(id);
		System.out.println("삭제됨");
	}
	
	public WebSocketSession find(String id) {
		return map.get(id);
	}
	
	public List<WebSocketSession> getWs()  {
		List<WebSocketSession> list = new ArrayList<WebSocketSession>();
		
		Set<String> set = map.keySet();
		for(String key : set) {
			list.add(map.get(key));
		}
		return list;
	}
}
