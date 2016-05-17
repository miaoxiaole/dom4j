package org.cherry.wechat.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.cherry.wechat.message.*;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;

/**
 * 创建一个消息处理工具类，将请求的消息类型，响应的消息类型，事件类型，
 * 解析XML请求的方法以及响应消息对象转xml的方法全部封装起来
 *
 * @author wuyue
 * @data 2016/5/16
 */
public class MessageUtil {
    public static final String REQ_NESSAGE_TYPE_TEXT="text";
    public static final String REQ_NESSAGE_TYPE_IMAGE="image";
    public static final String REQ_NESSAGE_TYPE_VOICE="voice";
    public static final String REQ_NESSAGE_TYPE_VIDEO="video";
    public static final String REQ_NESSAGE_TYPE_LOCATION="location";
    public static final String REQ_NESSAGE_TYPE_LINK="link";

    public static final String REQ_NESSAGE_TYPE_EVENT="event";

    public static final String RESP_NESSAGE_TYPE_TEXT="text";
    public static final String RESP_NESSAGE_TYPE_IMAGE="image";
    public static final String RESP_NESSAGE_TYPE_VOICE="voice";
    public static final String RESP_NESSAGE_TYPE_VIDEO="video";
    public static final String RESP_NESSAGE_TYPE_LOCATION="music";
    public static final String RESP_NESSAGE_TYPE_LINK="news";

    public static final String EVENT_TYPE_SUBSCRIBE="subscribe";
    public static final String EVENT_TYPE_UNSUBSCRIBE="unsubscribe";

    /**
     * 解析微信发来的请求XML
     *
     * @param request
     * @return
     * @throws Exception
     */

    public static HashMap<String,String> parseXml(HttpServletRequest request) throws Exception {
        HashMap<String,String>map = new HashMap<String, String>();

        SAXReader reader = new SAXReader();
        Document doc = reader.read(request.getInputStream());
        Element root = doc.getRootElement();
        //调用级联的解析方法
        recursiveParseXML(root,map);

        return map;
    }

    private static void recursiveParseXML(Element root,HashMap<String,String> map){
        //得到根节点的子节点列表
        List<Element> elementsList = root.elements();

        //判断有没有子元素列表
        if (elementsList.size()==0){
            System.out.print(root.getName()+"=>"+root.getStringValue());
            map.put(root.getName(),root.getTextTrim());
        }
        else{
            //遍历
            for (Element e: elementsList){
                recursiveParseXML(e,map);
            }
        }
    }

    /**
     * xml转义，定义新的xstream
     */
    private static XStream xStream = new XStream(new XppDriver(){
        @Override
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out){
                //对所有xml节点都进行CDATA标记
                boolean cdata = true;


                public void startNode(String name, Class clazz){
                    super.startNode(name, clazz);
                }

                protected void writeText(QuickWriter writer, String text){
                    if (cdata){
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    }
                    else
                        writer.write(text);
                }

            };
        }
    });

    //文本消息
    public static String messageToXML(TextMessage textMessage){

        xStream.alias("xml",TextMessage.class);
        return xStream.toXML(textMessage);

    }

    //图片消息
    public static String messageToXML(ImageMessage imageMessage){

        xStream.alias("xml",ImageMessage.class);
        return xStream.toXML(imageMessage);

    }

    //音乐消息
    public static String messageToXML(MusicMessage musicMessage){

        xStream.alias("xml",MusicMessage.class);
        return xStream.toXML(musicMessage);

    }

    //图文消息
    public static String messageToXML(NewsMessage newsMessage){

        xStream.alias("xml",NewsMessage.class);
        xStream.alias("item",Artical.class);
        return xStream.toXML(newsMessage);

    }

    //视频消息
    public static String messageToXML(VideoMessage videoMessage){

        xStream.alias("xml",VideoMessage.class);
        return xStream.toXML(videoMessage);

    }
    //语音消息
    public static String messageToXML(VoiceMessage voiceMessage){

        xStream.alias("xml",VoiceMessage.class);
        return xStream.toXML(voiceMessage);

    }

}
