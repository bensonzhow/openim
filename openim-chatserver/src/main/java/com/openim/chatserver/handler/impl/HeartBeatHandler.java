package com.openim.chatserver.handler.impl;

import com.alibaba.fastjson.JSONObject;
import com.openim.chatserver.Constants;
import com.openim.chatserver.handler.IMessageHandler;
import com.openim.common.bean.DeviceMsgType;
import io.netty.channel.Channel;

/**
 * Created by shihuacai on 2015/7/22.
 */
//@Component
public class HeartBeatHandler implements IMessageHandler {
    @Override
    public void handle(JSONObject jsonObject, HandlerChain handlerChain, Channel channel) {
        if(jsonObject != null){
            int type = jsonObject.getIntValue(Constants.deviceMsgField_Type);
            if(type == DeviceMsgType.HEART_BEAT){

            }else{
                handlerChain.handle(jsonObject,handlerChain, channel);
            }
        }
    }
}
