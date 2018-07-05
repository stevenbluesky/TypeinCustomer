package cn.com.isurpass.zufang.typeincustomer.util;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenKeeper {
    private volatile static TokenKeeper tokenKeeper = null;
    private static final String code = ReadConfig.get("restCode");
    private static final String password = ReadConfig.get("restPassword");
    private String token = "";
    private static final Logger logger = LoggerFactory.getLogger(TokenKeeper.class);
//    private long time = -1;

    private TokenKeeper() {
    }

    //通过TokenKeeper.getToken()获得token
    public static String getToken() {
        if (tokenKeeper == null) {
            synchronized (TokenKeeper.class) {
                if (tokenKeeper == null) {
                    tokenKeeper = new TokenKeeper();
                    getNewToken();
                }
            }
        }
        return tokenKeeper.token;
    }

    //在调用api下面判断返回值,如果为 (Integer)30300 ,则调用 TokenKeeper.getNewToken()重新获取token.
    public static void getNewToken(){
        for (int i = 0; i < 3; i++) {
            String rst = TokenKeeper.login();
            JSONObject jo = JSONObject.parseObject(rst);
            if (jo.getInteger("resultCode") == 0) {
                tokenKeeper.token = jo.getString("token");
                logger.info("New Token Created At:"+new SimpleDateFormat().format(new Date())+tokenKeeper.token);
                return ;
            } else if (jo.getInteger("resultCode") == 30010) {
                throw new RuntimeException();
            } else {
                continue;
            }
        }
        throw new RuntimeException();
    }

    private static String login(){
        String url = "https://app.aibasecloud.com/iremote/thirdpart/login";
        Map<String, String> map = new HashMap<>();
        map.put("code", TokenKeeper.code);
        map.put("password", TokenKeeper.password);
        String post = HttpsUtils.post(url, null, map, null);
        return post;
    }
}
