package io.github.biezhi.wechat.robot;

import com.google.gson.JsonObject;
import io.github.biezhi.wechat.Utils;
import io.github.biezhi.wechat.handle.AbstractMessageHandler;
import io.github.biezhi.wechat.model.Environment;
import io.github.biezhi.wechat.model.GroupMessage;
import io.github.biezhi.wechat.model.UserMessage;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static io.github.biezhi.wechat.api.WechatApi.JSON;

/**
 * 阿凡达机器人实现
 *
 * @author Sam
 *         27/07/2017
 */
public class AfdRobot extends AbstractMessageHandler {

    private String baseUrl = "";
    private String apiKey;
    private String apiSecret;

    public AfdRobot(Environment environment) {
        this.apiKey = environment.get("afd.api_key");
        this.apiSecret = environment.get("afd.api_secret");
    }

    @Override
    public void userMessage(UserMessage userMessage) {
        if (null == userMessage) {
            return;
        }
        String text = userMessage.getText();
        JsonObject raw_msg = userMessage.getRawMsg();
        String toUid = raw_msg.get("FromUserName").getAsString();
        String result = getResult(text);
        userMessage.sendText(result, toUid);
    }

    @Override
    public void groupMessage(GroupMessage groupMessage) {
        System.out.println(groupMessage);
        String text = groupMessage.getText();
        if (Utils.isNotBlank(text)) {
            String result = getResult(groupMessage.getText());
            groupMessage.sendText(result, groupMessage.getGroupId());
        }
    }

    private String getResult(String question) {
        String msg = "您好！打搅了！";

        return msg;
    }



}
