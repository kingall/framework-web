package cn.alldimensions.websocket.message;

import cn.alldimensions.websocket.endpoint.OnlineUserManagement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 消息推送线程池
 */
public class MsgPushPool{
    private static Logger logger = LoggerFactory.getLogger(MsgPushPool.class);
    /**
     * 消息推送池
     */
    private static List<ExecutorService> executorServicePool;

    /**
     * 初始化消息推送池
     */
    private MsgPushPool(){
        synchronized (executorServicePool){
            if(executorServicePool ==null){
                List<ExecutorService> newExecutorServicePool = new ArrayList<ExecutorService>();
                for(int i=0;i<10; i++){
                    newExecutorServicePool.add(Executors.newSingleThreadExecutor());
                }
            }
        }
    }

    public static Future<Boolean> commitMessage(String UID, String message){
        if(executorServicePool!=null){
            new MsgPushPool();
        }
        //根据UID的hashCode 获取线程编号
        int threadNum = UID.hashCode() % 10;
        return executorServicePool.get(threadNum).submit(new Callable() {
            @Override
            public Boolean call() throws Exception {
                try{
                    OnlineUserManagement.getOnlineUser(UID).getBasicRemote().sendText(message);
                    return true;
                }catch(Exception e){
                    logger.error("消息推送失败",e);
                    return false;
                }
            }
        });
    }
}
