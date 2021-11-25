package cn.alldimensions.websocket.message;

import java.io.IOException;
import java.util.List;

import javax.websocket.Session;

import cn.alldimensions.websocket.endpoint.OnlineUserManagement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

/**
 * 消息推送机
 * 
 * @author c.wei
 *
 */
public class MsgPusher {

	private static Logger logger = LoggerFactory.getLogger(MsgPusher.class);

	/**
	 * 根据socket session会话推送消息
	 * 
	 * @param session
	 * @param message
	 * @throws IllegalArgumentException
	 * @throws IOException
	 */
	private static void pushMsg(Session session, String message) throws IllegalArgumentException, IOException {
		if (session == null) {
			throw new IllegalArgumentException("websocket session is null!");
		} else {
			session.getBasicRemote().sendText(message);
		}

	}

	/**
	 * 根据接收UID推送消息
	 * 
	 * @param toUID
	 * @param message
	 * @throws IllegalArgumentException
	 * @throws IOException
	 */
	private static void pushMsg(String toUID, String message) throws Exception {
		try {
			OnlineUserManagement.getOnlineUser(toUID).getBasicRemote().sendText(message);
		} catch (IllegalArgumentException e) {
			logger.error(e.getLocalizedMessage(), e);
			throw new Exception("消息推送失败!",e);
		}
	}

	/**
	 * 发送广播消息
	 * 
	 * @param message
	 * @throws IOException
	 */
	private static void pushBroadcastMsg(String message) throws Exception {
		for (String sessionKey : OnlineUserManagement.getOnlineUserSet().keySet()) {
			MsgPushPool.commitMessage(sessionKey, message);
		}
	}

	/**
	 * 发送会话创建成功消息
	 * 
	 * @param session
	 * @throws IOException
	 */
	public static void pushSessionCreateSuccessMsg(Session session) {
		try {
			pushMsg(session, JSON.toJSONString(new BaseMsgBody(1000, "通信链接创建成功")));
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
		}
	}

	/**
	 * 发送会话被动下线消息
	 * 
	 * @param session
	 * @throws IOException
	 */
	public static void pushSessionOutLineMsg(Session session) {
		try {
			pushMsg(session, JSON.toJSONString(new BaseMsgBody(1009, "通信链接断开（其他客户端登陆）")));
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
		}
	}

	/**
	 * 发送下线通知
	 * 
	 * @param UID
	 * @throws IOException
	 */
	public static void pushOffLineNotice(String UID) {
		try {
			pushBroadcastMsg(JSON.toJSONString(new BaseMsgBody(2002, "用户下线通知", UID)));
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
		}
	}
	
	/**
	 * 发送下线通知
	 * 
	 * @param UID
	 * @throws IOException
	 */
	public static void pushOnLineNotice(String UID) {
		try {
			pushBroadcastMsg(JSON.toJSONString(new BaseMsgBody(2001, "用户上线通知", UID)));
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
		}
	}

	/**
	 * 推送点到点一般消息
	 * @param to
	 * @param msg
	 */
	public static void pushP2PMsg(String to, P2PMsgBody msg){
		try {
			pushMsg(to, JSON.toJSONString(new BaseMsgBody(0, "一般消息推送", msg)));
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
		}
	}
	/**
	 * 推送点到点一般消息
	 * @param UIDS
	 * @param msg
	 */
	public static void pushGroupMsg(List<String> UIDS, P2PMsgBody msg) throws Exception {
		for(String UID : UIDS) {
			try {
				pushMsg(UID, JSON.toJSONString(new BaseMsgBody(9, "群发消息推送", msg)));
			} catch (IOException e) {
				logger.error(e.getLocalizedMessage(), e);
			}
		}
	}

	/**
	 * 心跳消息
	 * @param UID
	 * @return
	 */
	public static boolean pushHeartbeat(String UID) {
		try {
			pushMsg(UID,JSON.toJSONString(new BaseMsgBody(8, "心跳消息", "test")));
			return true;
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
			return false;
		}
	}
}

class BaseMsgBody {
	/**
	 * 消息类型code
	 */
	private int code;
	/**
	 * 消息描述
	 */
	private String desc;
	/**
	 * 消息内容
	 */
	private String body;

	public BaseMsgBody(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public BaseMsgBody(int code, String desc, Object body) {
		this.code = code;
		this.desc = desc;
		this.body = JSON.toJSONString(body);
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
}
