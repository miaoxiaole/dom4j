package service;

import org.cherry.wechat.message.TextMessage;
import org.cherry.wechat.util.MessageUtil;

import javax.servlet.http.HttpServletRequest;
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

        //判断：对不同的消息请求发送不同的返回内容
        if (msgType.equals(MessageUtil.REQ_NESSAGE_TYPE_TEXT)){

        }
        //图片消息
        else if (msgType.equals(MessageUtil.REQ_NESSAGE_TYPE_IMAGE)){

        }
        //链接消息
        else if (msgType.equals(MessageUtil.REQ_NESSAGE_TYPE_LINK)){

        }else if (msgType.equals(MessageUtil.REQ_NESSAGE_TYPE_LOCATION)){

        }else if (msgType.equals(MessageUtil.REQ_NESSAGE_TYPE_VIDEO)){

        }else if (msgType.equals(MessageUtil.REQ_NESSAGE_TYPE_VOICE)){

        }else if (msgType.equals(MessageUtil.REQ_NESSAGE_TYPE_EVENT)){
            String eventType = requestMap.get("Event");
            if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)){

            }else{

            }

        }

        return null;

    }
}
