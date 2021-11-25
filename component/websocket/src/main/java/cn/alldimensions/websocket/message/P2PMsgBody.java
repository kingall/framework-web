package cn.alldimensions.websocket.message;

/**
 * 
 * @author cn.com.all
 * 
 *         基本消息体
 */
public class P2PMsgBody {
	// 消息发送UID
	private String from;
	// 消息接收UID
	private String to;
	// 消息类型
	private String type;
	// 消息內容
	private String body;

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
