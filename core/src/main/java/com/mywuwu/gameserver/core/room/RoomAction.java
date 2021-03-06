package com.mywuwu.gameserver.core.room;

/**
 * 房间处理类
 */
public interface RoomAction<T,D> {
    /**
     * 房间消息处理类
     * @param message 消息
     * @param context 当前房间上下文
     * @return
     */
    void roomAction(T message, D context);
}
