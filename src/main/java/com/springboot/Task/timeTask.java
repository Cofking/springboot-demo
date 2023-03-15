package com.springboot.Task;

import com.springboot.handle.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;


/**
 * @author ChenKang
 * @date 2023/3/14 11:09
 */
@Component
public class timeTask {

    @Autowired
    WebSocketServer webSocketServer;

//    @Scheduled(cron = "0/1 * * * * ? ")
    public void test() throws IOException {
        System.out.println("定时任务启动");
        for( Map.Entry<String, WebSocketServer>  x:webSocketServer.getWebSocket().entrySet()){
            WebSocketServer value = x.getValue();
            value.sendMessage(value.showThreadPoolInfor());
        }


    }
}
