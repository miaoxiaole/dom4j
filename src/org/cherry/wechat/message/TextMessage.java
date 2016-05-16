package org.cherry.wechat.message;

/**
 * @author wuyue
 * @data 2016/5/16
 */
public class TextMessage extends BaseMessage{

    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
