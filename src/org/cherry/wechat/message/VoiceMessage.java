package org.cherry.wechat.message;

/**
 * @author wuyue
 * @data 2016/5/16
 */
public class VoiceMessage extends BaseMessage{
    private Voice Voice;

    public Voice getVoice() {
        return Voice;
    }

    public void setVoice(Voice voice) {
        Voice = voice;
    }
}
