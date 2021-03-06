package cn.ark.community.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

import java.util.UUID;

/**
 * @program: community
 * @description:工具类
 * @author: Ark
 * @create: 2022-02-19 19:58
 **/
public class CommunityUtil {

    /**
     * 生成随机字符串
     * @return
     */
    public static String generateUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");

    }

    /**
     * md5加密
     * @param key
     * @return
     */
    public static String md5(String key){
        if (StringUtils.isBlank(key)){
            return null;
        }
        return DigestUtils.md5DigestAsHex(key.getBytes());
    }
}
