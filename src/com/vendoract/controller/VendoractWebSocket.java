package com.vendoract.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServlet;
import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.OnOpen;

import com.vendoract.model.VendorActService;


@ServerEndpoint(value = "/vendoract/VendoractWebSocket")
public class VendoractWebSocket extends HttpServlet {
	private static final Set<Session> connectedSessions = Collections.synchronizedSet(new HashSet<>());
	private VendorActService vaSvc;

	@OnOpen
	public void onOpen(@PathParam("userName") String userName, Session userSession, EndpointConfig config) throws IOException {
		connectedSessions.add(userSession);
		System.out.println("connect:111");
		vaSvc = new VendorActService();
	}

	@OnMessage
	// 當接收到資料時觸發(n次)(如同 servlet service)
	// 後端收到資料
	public void onMessage(Session userSession, String message) {
		for (Session session : connectedSessions) {
			if (session.isOpen() && "1".equals(message)) {
				Integer count = vaSvc.findNoCheck();
				System.out.println("onMessage" + message);
				session.getAsyncRemote().sendText(count.toString());
			}
				// sendText 等於前端 觸發 onMessage
		}
		System.out.println("vendor Message received: " + message);
	}

	@OnClose
	// 當連線斷線時觸發(1次)(如同 servlet destroy)
	public void onClose(Session userSession, CloseReason reason) {
		connectedSessions.remove(userSession);
		String text = String.format("session ID = %s, disconnected; close code = %d; reason phrase = %s",
				userSession.getId(), reason.getCloseCode().getCode(), reason.getReasonPhrase());
		System.out.println("vendor" + text);
	}

	@OnError
	// 當連線或資料傳輸發生例外時觸發
	public void onError(Session userSession, Throwable e) {
		System.out.println("Error: " + e.toString());
	}

}
