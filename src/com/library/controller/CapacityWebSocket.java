package com.library.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;
import com.library.model.ServletAwareConfig;

@ServerEndpoint(value = "/CapacityWebSocket/Capacity", configurator=ServletAwareConfig.class)
public class CapacityWebSocket extends HttpServlet {
	private static final Set<Session> connectedSessions = Collections.synchronizedSet(new HashSet<>());
	private EndpointConfig config;
	private int capacity;
	private Gson gson = new Gson();
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	/*
	 * 如果想取得HttpSession與ServletContext必須實作
	 * ServerEndpointConfig.Configurator.modifyHandshake()，
	 * 參考https://stackoverflow.com/questions/21888425/accessing-servletcontext-and-
	 * httpsession-in-onmessage-of-a-jsr-356-serverendpoint
	 */
	// 當連線建立成功時一次性的執行(如同 servlet init)
	// Session 為 webScoket 的 session, 非 servlet 的 session
	@OnOpen
	public void onOpen(@PathParam("userName") String userName, Session userSession, EndpointConfig config) throws IOException {
		connectedSessions.add(userSession);
		this.config = config;
		HttpSession httpSession = (HttpSession) config.getUserProperties().get("httpSession");
        ServletContext servletContext = httpSession.getServletContext();
		capacity = (int) servletContext.getAttribute("capacity");
		System.out.println("in");
	}

	@OnMessage
	// 當接收到資料時觸發(n次)(如同 servlet service)
	// 後端收到資料
	public void onMessage(Session userSession, String message) {
		for (Session session : connectedSessions) {
			if (session.isOpen() && "1".equals(message)) {
				Map<Integer, String> res = new HashMap<>();
				String nowTime = df.format(new java.util.Date());				
				res.put(new Integer(capacity), nowTime);
				session.getAsyncRemote().sendText(gson.toJson(res));
			}
				// sendText 等於前端 觸發 onMessage
		}
		System.out.println("Message received: " + message);
	}

	@OnClose
	// 當連線斷線時觸發(1次)(如同 servlet destroy)
	public void onClose(Session userSession, CloseReason reason) {
		connectedSessions.remove(userSession);
		String text = String.format("session ID = %s, disconnected; close code = %d; reason phrase = %s",
				userSession.getId(), reason.getCloseCode().getCode(), reason.getReasonPhrase());
		System.out.println(text);
	}

	@OnError
	// 當連線或資料傳輸發生例外時觸發
	public void onError(Session userSession, Throwable e) {
		System.out.println("Error: " + e.toString());
	}

}
