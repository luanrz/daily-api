package cn.luanrz.daily.api.base.common;

import java.util.UUID;
/**
 * ID生成器
 */
public class IdGenerator {

    private static final String WECHAT_USERNAME_PREFIX = "wx_";

    public static String generateUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    public static String getWechatUsername(String uuid){
        return WECHAT_USERNAME_PREFIX + uuid.substring(26);
    }

}
