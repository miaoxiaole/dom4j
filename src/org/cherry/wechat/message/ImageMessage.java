package org.cherry.wechat.message;

/**
 * @author wuyue
 * @data 2016/5/16
 */
public class ImageMessage extends  BaseMessage {
        private  Image Image;

    public Image getImage() {
        return Image;
    }

    public void setImage(Image image) {
        Image = image;
    }
}
