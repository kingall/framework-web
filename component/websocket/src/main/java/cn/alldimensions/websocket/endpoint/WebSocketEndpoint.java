package cn.alldimensions.websocket.endpoint;

import cn.alldimensions.websocket.message.MsgPusher;
import cn.alldimensions.websocket.message.P2PMsgBody;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

/**
 * @author cn.com.all
 * @ServerEndpoint 挂在websocket服务点
 */
@ServerEndpoint("/websocket/{UID}")
@Component
public class WebSocketEndpoint {
	
	private static Logger logger = LoggerFactory.getLogger(WebSocketEndpoint.class);

	/**
	 * web客户端创建websocket连接调用
	 * 
	 * @param UID
	 * @param session
	 * @param config
	 */
	@OnOpen
	public synchronized void onOpen(@PathParam(value = "UID") String UID, Session session, EndpointConfig config) {

		logger.debug("当前登录用户为:{}", UID);

		// 判断用户是否已存在绘画信息,如果存在踢出当前回话
		if (OnlineUserManagement.isOnline(UID)) {
			//推送下线消息
			MsgPusher.pushSessionOutLineMsg(OnlineUserManagement.getOnlineUser(UID));
			//移除离线回话
			OnlineUserManagement.removeOfflineUser(UID);
		}
		//添加新用户消息
		OnlineUserManagement.addOnlineUser(UID, session);
		MsgPusher.pushSessionCreateSuccessMsg(session);
		MsgPusher.pushOnLineNotice(UID);
	}

	/**
	 * 客戶端链接关闭
	 * 
	 * @param UID
	 * @param session
	 */
	@OnClose
	public synchronized void onClose(@PathParam(value = "UID") String UID, Session session) {
		if (UID != null && !UID.isEmpty()) {
			if (OnlineUserManagement.isOnline(UID)) {
				OnlineUserManagement.removeOfflineUser(UID);// 从set中删除
				MsgPusher.pushOffLineNotice(UID);
			}
		}
	}

	/**
	 * 收到客户端消息后调用的方法
	 *
	 * @param message 客户端发送过来的消息
	 */
	@OnMessage
	public void onMessage(String message, Session session) {
		P2PMsgBody msg = JSON.parseObject(message, P2PMsgBody.class);
		MsgPusher.pushP2PMsg(msg.getTo(),msg);
	}

	/**
	 * 接收通讯错误消息
	 * @param UID
	 * @param session
	 * @param error
	 */
	@OnError
	public synchronized void onError(@PathParam(value = "UID") String UID, Session session, Throwable error) {
		logger.error("发生错误：{}，SESSION ID: {}", error.getMessage(), session.getId());
		if (OnlineUserManagement.isOnline(UID)) {
			OnlineUserManagement.removeOfflineUser(UID);
			MsgPusher.pushOffLineNotice(UID);
		}
	}
}
