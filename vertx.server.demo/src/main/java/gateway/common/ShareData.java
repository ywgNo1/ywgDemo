package gateway.common;

import io.vertx.core.shareddata.Shareable;

/**
 * 用于在vertx之间共享数据
 * @author yangwg
 * @time 2018年6月18日  下午2:04:35
 */
public class ShareData implements Shareable {
    private Object data;

    public ShareData() {
    }

    public ShareData(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
