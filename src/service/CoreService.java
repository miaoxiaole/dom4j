package service;

import org.cherry.wechat.message.TextMessage;
import org.cherry.wechat.util.MessageUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by YF on 2016/5/17.
 */
public class CoreService {

    public static String processRequest(HttpServletRequest request) throws Exception {
        String  respXML = null;
        //解析微信发送的请求
        HashMap<String,String> requestMap = MessageUtil.parseXml(request);
        String fromUserName= requestMap.get("FromUserName");
        String toUserName = requestMap.get("ToUserMame");
        String msgType = requestMap.get("MsgType");

        TextMessage tm = new TextMessage();
        tm.setToUserName(fromUserName);
        tm.setFromUserName(toUserName);
        tm.setMsgType(MessageUtil.REQ_NESSAGE_TYPE_TEXT);
        tm.setCreateTime(new Date().getTime());


        //判断：对不同的消息请求发送不同的返回内容
        if (msgType.equals(MessageUtil.REQ_NESSAGE_TYPE_TEXT)){
            tm.setContent("您发送的是文本消息");
        }
        //图片消息
        else if (msgType.equals(MessageUtil.REQ_NESSAGE_TYPE_IMAGE)){
            tm.setContent("图片");
        }
        //链接消息
        else if (msgType.equals(MessageUtil.REQ_NESSAGE_TYPE_LINK)){
            tm.setContent("链接");

        }else if (msgType.equals(MessageUtil.REQ_NESSAGE_TYPE_LOCATION)){
            tm.setContent("位置");

        }else if (msgType.equals(MessageUtil.REQ_NESSAGE_TYPE_VIDEO)){
            tm.setContent("视频");

        }else if (msgType.equals(MessageUtil.REQ_NESSAGE_TYPE_VOICE)){
            tm.setContent("语音");

        }else if (msgType.equals(MessageUtil.REQ_NESSAGE_TYPE_EVENT)){
            String eventType = requestMap.get("Event");
            if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)){
                tm.setContent("欢迎关注");

            }else{

            }

        }

        return MessageUtil.messageToXML(tm);

    }
}
