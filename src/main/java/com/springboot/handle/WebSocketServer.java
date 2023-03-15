package com.springboot.handle;

import cn.hutool.core.util.StrUtil;
import com.springboot.controller.ThreadController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;


/**
 * websocket的处理类。作用相当于HTTP请求中的controller
 *
 * @author ChenKang
 * @date 2023/3/13 15:18
 */
@ServerEndpoint(value = "/websocket/{userId}")
@Component
@Slf4j
public class WebSocketServer {

    private static ThreadController threadController;

    //注入spring中的 Bean
    public static void setApplicationContext(ApplicationContext context) {
        threadController = context.getBean(ThreadController.class);
    }


    /**
     * 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的
     */

    private static int onlineCount = 0;

    /**
     * concurrent包的线程安全Map，用来存放每个客户端对应的MyWebSocket对象
     */
    private static ConcurrentHashMap<String, WebSocketServer> webSocketMap = new ConcurrentHashMap<>(10);


    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */

    private Session session;
    private String userId;


    /**
     * 连接建立成功调用的方法
     */

    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        this.session = session;
        this.userId = userId;
        //加入map
        webSocketMap.put(userId, this);
        addOnlineCount();           //在线数加1
        log.info("用户{}连接成功,当前在线人数为{}", userId, getOnlineCount());
        try {
            sendMessage("你好：" + this.userId);
        } catch (IOException e) {
            log.error("IO异常");
        }
    }


    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        //从map中删除
        webSocketMap.remove(userId);
        subOnlineCount();           //在线数减1
        log.info("用户{}关闭连接！当前在线人数为{}", userId, getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) throws IOException, InterruptedException {
        log.info("来自客户端用户：{} 消息:{}", userId, message);

    }

    public String  showThreadPoolInfor()  {
        return threadController.showThread();
    }
    /**
     * 发生错误时调用
     *
     * @OnError
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("用户错误:" + this.userId + ",原因:" + error.getMessage());
        error.printStackTrace();
    }

    /**
     * 向客户端发送消息
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
        //this.session.getAsyncRemote().sendText(message);
    }

    /**
     * 通过userId向客户端发送消息
     */
    public void sendMessageByUserId(String userId, String message) throws IOException {
        log.info("服务端发送消息到{},消息：{}", userId, message);
        if (StrUtil.isNotBlank(userId) && webSocketMap.containsKey(userId)) {
            webSocketMap.get(userId).sendMessage(message);
        } else {
            log.error("用户{}不在线", userId);
        }

    }

    /**
     * 群发自定义消息
     */
    public static void sendInfo(String message) throws IOException {
        for (String item : webSocketMap.keySet()) {
            try {
                webSocketMap.get(item).sendMessage(message);
            } catch (IOException e) {
                continue;
            }
        }
    }
    public  ConcurrentHashMap<String, WebSocketServer>  getWebSocket() {
        return webSocketMap;
    }
    public  synchronized int getOnlineCount() {
        return onlineCount;
    }

    public  synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public  synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }

}
