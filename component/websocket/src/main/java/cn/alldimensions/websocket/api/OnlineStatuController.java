package cn.alldimensions.websocket.api;

import cn.alldimensions.websocket.endpoint.OnlineUserManagement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 获取用状态API
 */

@RestController
@RequestMapping("api")
public class OnlineStatuController {
    /**
     * 获取当前在线人数
     */
    @RequestMapping("/getOnlineCount")
    public int getOnlineCount(){
        return OnlineUserManagement.getOnlineCount();
    }

    /**
     * 获取用户在线列表
     * @return
     */
    public String[] getOnlineUserList(){
        return OnlineUserManagement.getOnlineUserList();
    }
}
