package cn.alldimensions.websocket.endpoint;

import cn.alldimensions.websocket.message.MsgPusher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.websocket.Session;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 在线用户管理
 */
@Component
@EnableScheduling
public class OnlineUserManagement {

    private static Logger logger = LoggerFactory.getLogger(OnlineUserManagement.class);

    /**
     * 在线数统计
     */
    private static final AtomicInteger ONLINE_COUNT = new AtomicInteger(0);

    /**
     * 记录各session回话集合，根据UID关键字存取
     */
    private static ConcurrentHashMap<String, Session> WEBSOCKET_SET = new ConcurrentHashMap<String, Session>();


    /**
     * 获取在线用户
     * @param uid
     * @return
     */
    public static Session getOnlineUser(String uid) {
        return WEBSOCKET_SET.get(uid);
    }

    /**
     * 移除在线用户
     * @param uid
     */
    public static void removeOfflineUser(String uid) {
        WEBSOCKET_SET.remove(uid);
        int cnt = ONLINE_COUNT.decrementAndGet();
        logger.debug("有连接加入，当前连接数为：{}", cnt);
    }

    /**
     * 添加在线人数
     * @param uid
     * @param session
     */
    public static void addOnlineUser(String uid, Session session) {
        WEBSOCKET_SET.put(uid,session);
        int cnt = ONLINE_COUNT.incrementAndGet();
        logger.debug("有连接加入，当前连接数为：{}", cnt);
    }

    /**
     * 判断用户是否在线
     * @param uid
     * @return
     */
    public static boolean isOnline(String uid) {
        return WEBSOCKET_SET.get(uid) == null? false:true;
    }

    /**
     * 获取在线用户set
     * @return
     */
    public static ConcurrentHashMap<String, Session> getOnlineUserSet() {
        return WEBSOCKET_SET;
    }

    public static int getOnlineCount() {
        return  ONLINE_COUNT.get();
    }

    public static String[] getOnlineUserList() {
        return (String[]) WEBSOCKET_SET.keySet().toArray();
    }

    /**
     * 检查用户在线状态
     */
    @Scheduled(cron = "0/5 * * * * ?")
    private void checkOnlineStatus() {
        WEBSOCKET_SET.forEach((uid,session) ->{
            if(!MsgPusher.pushHeartbeat(uid)){
                removeOfflineUser(uid);
            };
        });
    }
}
