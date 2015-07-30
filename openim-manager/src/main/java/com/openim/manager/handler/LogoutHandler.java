package com.openim.manager.handler;

import com.alibaba.fastjson.JSONObject;
import com.openim.common.im.DeviceMsgField;
import com.openim.common.im.LoginStatus;
import com.openim.manager.bean.User;
import com.openim.manager.cache.login.ILoginCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * Created by shihc on 2015/7/30.
 */
@Component
public class LogoutHandler implements IMessageHandler<JSONObject> {

    private static final Logger LOG = LoggerFactory.getLogger(LogoutHandler.class);

    @Autowired
    private ILoginCache loginCache;

    @Override
    public void handle(JSONObject jsonObject, HandlerChain handlerChain) {
        try {
            String loginId = jsonObject.getString(DeviceMsgField.loginId);
            //String serverQueue = jsonObject.getString(DeviceMsgField.serverQueue);
            if(!StringUtils.isEmpty(loginId)){
                User user = loginCache.get(loginId);
                if(user != null){
                    user.setLoginStatus(LoginStatus.offline);
                    loginCache.add(loginId, user);
                }
                //通知其好友下线了，待完成
            }else{
                LOG.error("下线信息不全：loginId:{}", loginId);
            }
        }catch (Exception e){
            LOG.error(e.toString());
        }

        /*int type = jsonObject.getIntValue(DeviceMsgField.type);
        if (type == DeviceMsgType.LOGOUT) {

        } else {
            handlerChain.handle(jsonObject, handlerChain);
        }*/

    }
}
