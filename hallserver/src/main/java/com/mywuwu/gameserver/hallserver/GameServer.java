package com.mywuwu.gameserver.hallserver;


import com.mywuwu.gameserver.core.TransferData;
import com.mywuwu.gameserver.core.action.DispatcherAction;
import com.mywuwu.gameserver.core.websocket.GameWebSocket;
import com.mywuwu.gameserver.core.websocket.GameWebSocketSession;
import com.mywuwu.gameserver.hallserver.config.HallConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yeauty.annotation.ServerEndpoint;


@Component
@ServerEndpoint(prefix = "netty-websocket")
public class GameServer extends GameWebSocket {

    @Autowired
    private DispatcherAction dispatcherAction;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private HallConfig hallConfig;

    @Override
    protected boolean receiveHandle(GameWebSocketSession session, int channel, int protocol, byte[] buffer) {
        String channelString = hallConfig.getRoutes().get(String.valueOf(channel));
//        Map<String, Object> map = new HashMap<>();
//        map.put("playerLowerlimit","1");
//        map.put("playerUpLimit","5");
//        map.put("xiaZhuTop","2");
//        map.put("juShu","15");
//        session.setRoomNumber("10006");
//        TransferData transferData = new TransferData(session, "yingsanzhang", 1003, buffer);

        if (channelString.equals(hallConfig.getName())) {
            dispatcherAction.createAction(protocol);
        } else {
            this.redisTemplate.convertAndSend(channelString, new TransferData(session, channelString, protocol, buffer));
        }
        return true;
    }

    @Override
    protected void openHandle(GameWebSocketSession session) {
        if(session.getChannel() != null && !session.getChannel().isEmpty())
        {
            this.redisTemplate.convertAndSend(session.getChannel(), new TransferData(session, "", 1002, "sadfasdfasdfas".getBytes()));
        }
    }

    @Override
    protected void closeHandle(GameWebSocketSession session) {
        if(session.getChannel() != null && !session.getChannel().isEmpty()){
            this.redisTemplate.convertAndSend(session.getChannel(), new TransferData(session, "", 1001, null));
        }
    }

//    @Override
//    protected void onMessageHandle(TransferData transferData) {
//        if(transferData != null){
//            this.redisTemplate.convertAndSend(transferData.getChannel(), transferData);
//        }
//    }

}
