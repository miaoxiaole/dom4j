package org.cherry.wechat.message;

/**
 * @author wuyue
 * @data 2016/5/16
 */
public class VideoMessage extends BaseMessage {
    private  Video Video;

    public Video getVideo() {
        return Video;
    }

    public void setVideo(Video video) {
        Video = video;
    }
}
