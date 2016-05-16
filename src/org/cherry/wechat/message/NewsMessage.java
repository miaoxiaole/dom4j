package org.cherry.wechat.message;

import java.util.List;

/**
 * @author wuyue
 * @data 2016/5/16
 */
public class NewsMessage extends  BaseMessage {
    private int ArticaLCount;
    private List<Artical> Articals;

    public int getArticaLCount() {
        return ArticaLCount;
    }

    public void setArticaLCount(int articaLCount) {
        ArticaLCount = articaLCount;
    }

    public List<Artical> getArticals() {
        return Articals;
    }

    public void setArticals(List<Artical> articals) {
        Articals = articals;
    }
}
